package com.eps.Flink

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

object DataSetWordCount {

  //Source 是DataSet

  def main(args: Array[String]): Unit = {

    println("-------------------Start calculate----------------")
    val env = ExecutionEnvironment.getExecutionEnvironment

    val text =env.fromElements(
      "Who's there?" +
              "I think I hear them. " +
              "Standm ho! Who's there?")
    println("-------------------Start calculate----------------")
    val counts = text.flatMap { _.toLowerCase.split("\\W+") filter {_.nonEmpty}}
      .map {( _, 1)}
      .groupBy(0)
      .sum(1)
    println("-------------------Start calculate----------------")
    counts.print()
    println("-------------------Start calculate----------------")

  }//main()

}
