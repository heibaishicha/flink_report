package com.eps.Flink.Exercises.JiCheng.PriceDescrible
//
//abstract class Item {
//      def price :Double
//      def description:String
//}
//
//class SimpleItem(override val price:Double,override val description:String)extends Item {
//      val itemList = scala.collection.mutable.ArrayBuffer[Item]()
//
//      def addItem(item: Item): Unit ={
//        itemList += item
//      }
//
//      override def price = {
//        var p:Double = 0.0
//        itemList.foreach(i => p = p+i.price)
//        p
//      }
//
//      override def description = {
//        var des = ""
//        itemList.foreach(i => des=des+i.description+"")
//      }
//}
//
//object ItemTest{
//      val bundle = new Bundle
//
//}
