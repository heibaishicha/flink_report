package com.eps.Flink

import org.apache.flink.api.common.functions.FilterFunction
import org.apache.flink.cep.PatternSelectFunction
import org.apache.flink.cep.scala.CEP
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.scala._

object CepApp {

  def main(args: Array[String]) {

//    val env = StreamExecutionEnvironment.getExecutionEnvironment
//    val input = env.fromElements(new TemperatureEvent("xyz", 22.0))
//
//    val pattern:Pattern[TemperatureEvent,_] = Pattern.begin[TemperatureEvent]("first").subtype(classOf[TemperatureEvent]).where(new FilterFunction[TemperatureEvent] {
//      override def filter(value: TemperatureEvent): Boolean = {
//        if (value.temperature >= 26.0) true else false
//
//      }
//    }).within(Time.seconds(10))
//
//
//    val patternStream: DataStream[Alert] = CEP.pattern[TemperatureEvent](input, pattern).select(new PatternSelectFunction[TemperatureEvent, Alert] {
//      override def select(pattern: util.Map[String, TemperatureEvent]): Alert = {
//
//        new Alert("Temperature Rise Detected ...")
//      }
//    })
//
//    patternStream.print()
//    env.execute("CEP on Temperature Sensor")
//
 }

}
