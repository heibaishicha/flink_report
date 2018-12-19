package com.eps.Flink

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time


object SocketWindowWordCount {

//  val env: StreamExecutionEnvironment  = StreamExecutionEnvironment.getExecutionEnvironment
//  val text: DataStream[String] = env.socketTextStream("10.202.42.36",9000, '\n')
//
//  val windowCounts = text.flatMap(w => w.split("\\s")).map(w => WordWithCount(w, 1)).keyBy("word").timeWindow(Time.seconds(5), Time.seconds(1)).sum("count")
//  windowCounts.print().setParallelism(1)
//  env.execute("Socket Window WordCount")
//
//  case class WordWithCount(value: String, i: Long)
}





