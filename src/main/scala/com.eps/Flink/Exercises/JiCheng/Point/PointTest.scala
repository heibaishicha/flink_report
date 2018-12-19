package com.eps.Flink.Exercises.JiCheng.Point

object PointTest {
       def main(args: Array[String]): Unit = {
         val point = new Point(2,3)
         val lpoint = new LabelPoint("圆形",2,3)
         println(point)
         println(lpoint)
       }
}
