package com.eps.Flink.Exercises

import java.io.File
import scala.collection.mutable


object ScalaExercises {


       def main(args: Array[String]): Unit = {

          //测试xn()
          print(xn(2,-3) + "\n")
          print(xn(2,0) + "\n")
          print(xn(5,-9) + "\n")

          //单词计数
          print(maps.foreach(println) + "\n")

          //链式映射表
          print(map.foreach(println) + "\n")

          //属性转ScalaMap
          import scala.collection.JavaConversions.propertiesAsScalaMap
          val props:scala.collection.Map[String,String] = System.getProperties()
          val keys = props.keySet
          val keylens = for(i <- keys) yield i.length()
          val keymaxlen = keylens.max
          for(key <- keys){
                 print(key)
                 print("   "*(keymaxlen-key.length()))
                 print("|")
                 println(props(key))
          }

          //
          val array = Array(1,1,1,2,3,4,5,6,7,7,8,8,99,12)
          val tuple:(Int,Int,Int) = iteqt(array,3)
          println("x>3的数量:" + tuple._1)
          println("x=3的数量:" + tuple._2)
          println("x<3的数量:" + tuple._3)

          //


       }//main() end

       //数组相关操作
       //定长数组：Array
       val nums = new Array[Int](100)           //初始化元素为0
       val strNum = new Array[String](100)      //初始化元素为null
       print("###利用Scala的推断性！")
       val strNNum = Array("BBC","AKKA")

       //变长数组：缓冲 ArrayBuffer
       import scala.collection.mutable.ArrayBuffer
       val eps_num = ArrayBuffer[Int]()         //或者ArrayBuffer[int],一个空的数组缓冲，准备存放整数
       eps_num += 1                             //在尾端添加元素
       eps_num +=(1,2,3,4,5,6,7)                //在尾端添加多个元素
       eps_num ++= Array(6,3,7,8)               //追加任何集合
       eps_num.trimEnd(5)                       //移除最后5个元素

       //任意位置操作缓冲数组（变长数组）
       eps_num.insert(2,6)                      //下标2之前插入元素6
       eps_num.insert(2,3,4,4,5,6,7,8)          //下标2之前插入多个元素
       eps_num.remove(2)                        //下标2之前移除1个元素
       eps_num.remove(2,3)                     //下标2之前移除3个元素

       //变长数组转成定长数组
       eps_num.toArray

       //定长数组转成变长数组
       nums.toBuffer

       //遍历定长数组和缓冲数组：全遍历、条件遍历
       for(i <- 0 until eps_num.length){       //全遍历
           print(i+ ":" + eps_num(i))
       }

       for(i <- 0 until (eps_num.length,2)){   //条件遍历
         print(i+ ":" + eps_num(i))
       }

       //for中的推导式和守卫
       val a = Array(1,2,3,4,5,6,7,8)
       val result = for(elem <-a) yield  2*elem
       for(elem <- a if elem==0)yield 2*elem
       a.filter(_%2 == 0).map(2*_)
       a.filter{_%2 ==0} map{2*_}

       //涉及数组的常用算法
       Array(1,7,2,8,9).sum
       ArrayBuffer("ZhangShan","aaa","ddd","cc","ddd").max   //"ZhangShan"
       val b = ArrayBuffer(1,7,8,9,2,3,5)
       //val aSorted = b.sorted(_ < _)

       //显示数组内容
       a.mkString("and").foreach(print)
       a.mkString("<",",",">").foreach(print)
       b.toString()  //报告了类型，便于调试

       //多维数组
       val matrix = Array.ofDim[Double](3,4)  //创建3*4矩阵
       //matrix(3)(2)                                       //访问matrix(3)(2)的元素

       //与Java的相互操作
       import scala.collection.JavaConversions.bufferAsJavaList
       import scala.collection.mutable.ArrayBuffer
       val command = ArrayBuffer("ls","cmd","hdfs","/home/cpp","eps","kk","mmk")
       val pd = new ProcessBuilder(command)               //Scala向Java的转换

       import scala.collection.mutable.Buffer
       import scala.collection.JavaConversions.asScalaBuffer
       val cmd:Buffer[String] = pd.command()              //Java到Scala转换

       //递归函数
       def fac(n:Int):Int = {if(n<=0) 1 else n*fac(n-1)}

       //变长参数
       def sum(args:Int*): Unit ={
              var result = 0
              for(arg <-args){
                     result += arg
              }
              result
       }
       val s = sum(1,2,3,4,5,6)                 //可以使用任意多的参数来调用该函数

       //过程
       def box(s:String){
              var border = "-"*s.length+"--\n"
              println(border + "|" + s + "|\n" + border)
       }
       def boxx(s:String): Unit ={

       }

       //懒值
       lazy val words = scala.io.Source.fromFile("/usr/share/dict/words").mkString

       //######异常
       //异常捕获
//       override def process(s:URL){
//             return s
//       }
//       val url = ""
//       try{
//              process(new URL("http://www.hao123.com"))
//       }catch {
//              case _: MalformedURLException => println("Bad URL:"+ url)
//              case ex:IndexOutOfBoundsException => ex.printStackTrace()
//
//       }

       //Exercise01 编写函数计算x^n,其中n是整数
       def xn(x:Double,n:Int): Double ={
              if(n == 0)
                     1
              else if(n<0)
                     1/xn(x,-n)
              else if (n%2 == 0)
                     xn(x,n/2)*xn(x,n/2)
              else
                     x*xn(x,n-1)
       }

       //单词统计
       val in = new java.util.Scanner(new File("E:\\Campanys\\CompanyWork\\EPS\\EPS_ITEM\\flink_report\\src\\main\\resources\\data\\input\\CSData\\file"))
       val maps = new mutable.HashMap[String,Int]
       var key:String = null
       while(in.hasNext()){
              key = in.next()
              maps(key) = maps.getOrElse(key,0)+1
       }

       //定义一个链式哈希映射
       val map = new mutable.LinkedHashMap[String,Int]
       map += ("MONDAY" -> java.util.Calendar.MONDAY)
       map += ("TUESDAY" -> java.util.Calendar.TUESDAY)
       map += ("THURSDAY" -> java.util.Calendar.THURSDAY)
       map += ("WEDNESDAY" -> java.util.Calendar.WEDNESDAY)
       map += ("FRIDAY" -> java.util.Calendar.FRIDAY)
       map += ("SATURDAY" -> java.util.Calendar.SATURDAY)
       map += ("SUNDAY" -> java.util.Calendar.SUNDAY)


       //返回数组中的min与max的对偶
       def minmax(arr:Array[Int]) ={
              (arr.min,arr.max)
       }

       //返回数组中 <v, >v, =v的数量
       def iteqt(arr:Array[Int],v:Int)={
              val buf = arr.toBuffer
              (buf.count(_ < v),buf.count(_ == v),buf.count(_ >= v))
       }

       //字符串拉链
       val tuple = "Hello".zip("World")
       tuple.toMap
       tuple.foreach(println)

       //文件和正则表达式
       import scala.io.Source
       val source = Source.fromFile("E:\\Campanys\\CompanyWork\\EPS\\EPS_ITEM\\flink_report\\src\\main\\resources\\data\\input\\CSData\\test.txt","UTF-8")
       val linelterator = source.getLines()
       for(l <- linelterator){

       }
       source.close()

       //目录访问
       import java.io.File
       def subdirs(dir:File):Iterator[File] = {
              val children = dir.listFiles().filter(_.isDirectory)
              children.toIterator ++ children.toIterator.flatMap(subdirs(_))
       }
       //for(d <- subdirs(dir)) //处理

       //目录访问--函数对象处理
       import java.nio.file._
       implicit def makeFileVisitor(f:(Path) => Unit) = new SimpleFileVisitor[Path]{
              override def visitFile(p:Path,attrs:attribute.BasicFileAttributes) ={
                     f(p)
                     FileVisitResult.CONTINUE
              }
       }

       //Files.walkFileTree(dir.toPath,(f:Path) =>printf(f))

       //序列化

       //进程控制
       //import sys.process._
       //val results = "|s -a|.."!

       //正则表达式、正则表达式组
       val numPattern = "[0-9]+".r
       for(matchString <- numPattern.findAllIn("99 bottles,98 bottles")){}
       val numitemPattern = "([0-9]+)([a-z]+)".r

       //






}
