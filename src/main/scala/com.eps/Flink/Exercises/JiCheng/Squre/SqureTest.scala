package com.eps.Flink.Exercises.JiCheng.Squre

object SqureTest {
       def main(args: Array[String]): Unit = {
           val rect1 = new Squre()
           val rect2 = new Squre(2)
           import java.awt.Point
           val rect3 = new Squre(new Point(2,3),5)
           println(rect1)
           println(rect2)
           println(rect3)
       }
}
