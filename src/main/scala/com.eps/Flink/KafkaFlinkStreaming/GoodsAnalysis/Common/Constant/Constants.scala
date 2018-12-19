package com.eps.Flink.KafkaFlinkStreaming.GoodsAnalysis.Common.Constant

import org.apache.flink.api.java.utils.ParameterTool


/**
  *
  * */
object Constants {

       final val JDBC_CONF_PATH = "src/main/resources/jdbc.json"

       //加载系统配置文件
       final val propFile = ParameterTool.fromPropertiesFile("")


}

/**
  *配置文件中参数Key
  * */
object K{

   /*Comment*/
   final val IS_LOCAL = "flink.islocal"

   /*Kafka*/
   final val IS_LOCAL1 = ""



}

/**
  *
  * */
object V{



}