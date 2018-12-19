package com.eps.Flink

import java.util.Properties
import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.cep.PatternSelectFunction
import org.apache.flink.cep.nfa.aftermatch.AfterMatchSkipStrategy
import org.apache.flink.cep.pattern.conditions.IterativeCondition
import org.apache.flink.cep.scala.CEP
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.functions.timestamps.{BoundedOutOfOrdernessTimestampExtractor}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.api.scala._

object Main {

  def main(args:Array[String]):Unit = {

    val env:StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "bootstrap")
    properties.setProperty("group.id","com.flinklearn.main.Main")

    val source = new FlinkKafkaConsumer010[String]("mytopic",new SimpleStringSchema(),properties)

    val input:DataStream[JSONObject] = env.addSource(source)
      .map(line=>{
        var rtn:JSONObject = null
        try{
          rtn = JSON.parseObject(line).getJSONObject("payload")
        }catch{
          case ex:Exception => {
            ex.printStackTrace()
          }
        }
        rtn
      }).filter(line=>line!=null)

    val pattern = Pattern.begin[JSONObject]("first",AfterMatchSkipStrategy.skipPastLastEvent).where(new IterativeCondition[JSONObject] {
      override def filter(value: JSONObject, ctx: IterativeCondition.Context[JSONObject]): Boolean = {
        //目标主机被netcore漏洞扫描
        //41472 Netcore / Netis 路由器后门
        value.getLong("ruleid").equals(41472L)
      }
    }).oneOrMore.greedy.followedBy("second").where(new IterativeCondition[JSONObject] {
      override def filter(value: JSONObject, ctx: IterativeCondition.Context[JSONObject]): Boolean = {
        //主机主动发起gafgyt通信行为
        //41533 Gafgyt僵尸网络通信
        val iterator:java.util.Iterator[JSONObject] = ctx.getEventsForPattern("first").iterator()
        var tag = false
        if(value.getLong("ruleid").equals(41533L)){
          while (!tag&&iterator.hasNext){
            val curitem = iterator.next()
            if(curitem.getString("dip").equals(value.getString("sip")) && value.getLong("timestamp") > curitem.getLong("timestamp")){
              tag = true
            }
          }
        }
        tag
      }
    }).within(Time.minutes(30L))

    val patternStream = CEP.pattern(input,pattern)

    val result = patternStream.select(new PatternSelectFunction[JSONObject,JSONObject] {
      override def select(pattern: java.util.Map[String, java.util.List[JSONObject]]): JSONObject = {
        val first = pattern.get("first")
        val second = pattern.get("second")
        var startTime = first.get(0).getLong("timestamp")
        var endTime = second.get(0).getLong("timestamp")
        for(i <- 1 until first.size()){
          if(first.get(i).getLong("timestamp") < startTime){
            startTime = first.get(i).getLong("timestamp")
          }
        }
        for(i <- 1 until second.size()){
          if(second.get(i).getLong("timestamp") > endTime){
            endTime = second.get(i).getLong("timestamp")
          }
        }
        val sip = first.get(0).getString("sip")
        val dip = first.get(0).getString("dip")
        val info1 = second.get(0).getString("dip")
        val msg = "目的IP被Netcore/Netis攻击成功并开始进行Gafgypt通信"

        val obj:JSONObject = new JSONObject()
        obj.put("start_time", startTime)
        obj.put("end_time", endTime)
        obj.put("sip", sip)
        obj.put("dip", dip)
        obj.put("info1", info1)
        obj.put("msg", msg)
        obj
      }
    })
    result.print()

    env.execute("Event generating test")
  }
}
