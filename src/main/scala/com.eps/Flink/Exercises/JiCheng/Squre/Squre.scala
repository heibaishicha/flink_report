package com.eps.Flink.Exercises.JiCheng.Squre

import java.awt.Rectangle
import java.awt.Point
class Squre extends Rectangle{
       height = 0
       width = 0
       x=0
       y=0
       def this(p:Point,w:Int){
          this()
          this.height = w
          this.width = w
          this.x = p.x
          this.y = p.y
       }
       def this(width:Int){
          this(new Point(0,0),width)
       }
}

