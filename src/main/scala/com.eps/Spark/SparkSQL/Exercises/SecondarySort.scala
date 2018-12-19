package com.eps.Spark.SparkSQL.Exercises

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.rdd.RDD.rddToOrderedRDDFunctions
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object SecondarySort {

  def main(args: Array[String]) {

    //编译设置
    System.setProperty("hadoop.home.dir", "D:\\softwares\\Hadoop\\hadoop-common-2.6.0")

    //初始化运行环境
    val conf = new SparkConf().setAppName("Secondary Sort")
      .setMaster("local[*]")
    var sc = new SparkContext(conf)

    //日志级别
    sc.setLogLevel("Warn")

    //val file = sc.textFile("hdfs://localhost:9000/Spark/SecondarySort/Input/SecondarySort2.txt")
    val file = sc.textFile("E:\\Campanys\\CompanyWork\\EPS\\EPS_ITEM\\flink_report\\src\\main\\resources\\data\\input\\CSData\\SecondarySort2")

    val rdd = file.map(line => line.split(","))
                  .map(x=>((x(0),x(1)),x(3))).groupByKey().sortByKey(false)
                  .map(x => (x._1._1+"-"+x._1._2,x._2.toList.sortWith(_>_)))

    rdd.foreach(
      x=>{
        val buf = new StringBuilder()
        for(a <- x._2){
          buf.append(a)
          buf.append(",")
        }
        buf.deleteCharAt(buf.length()-1)
        println(x._1+" "+buf.toString())
      })

    sc.stop()

  }

}
