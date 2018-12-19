package com.eps.Flink.Exercises

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

object WordCount {


  def main(args: Array[String]): Unit = {

         val env = ExecutionEnvironment.getExecutionEnvironment
         val text = env.fromElements(
              "what's thtere?"+
              "I think I hear them. Stand, ho! Who's there?")
         val counts = text.flatMap {_.toLowerCase.split("\\W+") filter(_.nonEmpty)}
             .map{(_,1)}
             .groupBy(0)
             .sum(1)
         counts.print()


  }

}
