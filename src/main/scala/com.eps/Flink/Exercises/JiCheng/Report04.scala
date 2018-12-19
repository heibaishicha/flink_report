package com.eps.Flink.Exercises.JiCheng


import org.apache.flink.api.scala._
import org.apache.flink.table.api._

object Report04 {

       //对于批处理使用ExecutionEnvironment来替换StreamExecutionEnvironment
       val env = ExecutionEnvironment.getExecutionEnvironment

       //创建TableEnvironment,对于批处理程序BatchTableEnvironment替代StreamTableEnvironment
       val tableEnv = TableEnvironment.getTableEnvironment(env)

       //在环境中注册一个表类
       val orders = tableEnv.scan("")

       //


}
