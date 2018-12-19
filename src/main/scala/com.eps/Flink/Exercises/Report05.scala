package com.eps.Flink.Exercises

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.TableEnvironment


object Report05 {

       val env = StreamExecutionEnvironment.getExecutionEnvironment
       val tEnv = TableEnvironment.getTableEnvironment(env)


}
