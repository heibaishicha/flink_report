package com.eps.Flink.Exercises.JiCheng.Point

class LabelPoint(val label:String,override val x:Double,override val y:Double) extends Point(x,y) {
      override def toString: String = "label= " + label+ "+x="+x+"y= "+y
}
