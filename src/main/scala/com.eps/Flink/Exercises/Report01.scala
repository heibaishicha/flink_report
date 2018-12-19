package com.eps.Flink.Exercises

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.api.scala._
object Report01 {

       def main(args: Array[String]): Unit = {

         //Settiing the Envrinment
         val env = ExecutionEnvironment.getExecutionEnvironment

         val tableEnv = TableEnvironment.getTableEnvironment(env)

         //Get data sorce
         val path:String = "E:\\Campanys\\CompanyWork\\EPS\\EPS_ITEM\\flink_report\\src\\main\\resources\\data\\input\\CSData\\test.txt"

         //build a rdd
         val data = env.readTextFile(path)

         //Transfer
         val rdd = data.map(line =>{
           val strs = line.split("\n")
           (strs(0),strs(1),strs(2),strs(3))
         })

         rdd.print()

       }//main() end



}
