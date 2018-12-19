package com.eps.Flink.Exercises.JiCheng

import java.io.PrintWriter

object Index extends App{

        val FileName = "Index"
        val path = "E:\\Campanys\\CompanyWork\\EPS\\EPS_ITEM\\flink_report\\src\\main\\resources\\data\\input\\CSData\\"+FileName+".txt"
        val out = new PrintWriter(path)

        for(i <- 0 to 20){

            out.println(OutIndex(i))
          out.close()

          def OutIndex(n:Int)={
            val value = math.pow(2,n)
            ""*4 + value.toInt+""*(11-value.toString().size)+math.pow(2,-n)
          }

        }

}
