package com.eps.Flink.Exercises

import org.apache.flink.api.scala._


object Report03 {

  def main(args: Array[String]): Unit = {

          val env = ExecutionEnvironment.getExecutionEnvironment
          val text = env.fromElements(
            "Who's there? " +
                    "I think I hear them")

          val counts = text.flatMap{_.toLowerCase.split("\\W+") filter{_.nonEmpty}}
                           .map{(_,1)}
                           .groupBy(0)
                           .sum(1)

          print(">>>>>>>>>>>>>>>>>>>>>>>ComeOut!\n")
          println(counts.print())

  }


}
