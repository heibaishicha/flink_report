package com.eps.Flink

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.table.sinks.CsvTableSink

object Demo02 extends App{

       case class ID(id:String)
       case class Mark(col1:String,col2:String,col3:String,col4:String,col5:String,col6:String)

       val env = ExecutionEnvironment.getExecutionEnvironment
       val tavleEvn = TableEnvironment.getTableEnvironment(env)

       import org.apache.flink.api.scala._
       val ds1 = env.readCsvFile[ID]("C:\\Users\\zhangliming\\Desktop\\YBS\\epsitems\\flink_report\\src\\main\\resources\\data\\input\\CSData\\id.txt")
       val path = "/home/huangwei/桌面/tag.txt"
       val ds2= env.readCsvFile[Mark](
            filePath = path,
            lineDelimiter = "\n",
            fieldDelimiter = "\001",
            lenient = false,
            ignoreFirstLine = true
            //    includedFields = Array(0, 1, 2, 3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33),
            //    pojoFields = Array("col1","col2","col3","col4","col5","col6","col7","col8","col9","col10","col11","col12","col13","col14","col15","col16","col17","col18","col19","col20","col21","col22","col23","col24","col25","col26","col27","col28","col29","col30","col31","col32","col33","col34")
       )

       //Source
       tavleEvn.registerDataSet("ID",ds1)
       tavleEvn.registerDataSet("TAG",ds2)

       //Transformation
       val result = tavleEvn.sql("select * from TAG where col1 in (select id from ID)")

       //Sink
       val sink = new CsvTableSink("/home/huangwei/桌面/result/A.csv")
       result.writeToSink(sink)
       env.setParallelism(1)                                                                       //设置并发数  最后生成一个文件
       env.execute()
}
