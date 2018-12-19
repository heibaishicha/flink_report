package com.eps.Flink.Exercises.Actor

import akka.actor.ActorLogging

/**
  * 创建Actor
  *
  * */
object Example_02 extends App{

       import akka.actor.Actor
       import akka.actor.ActorSystem
       import akka.actor.Props

       class MyActor extends Actor with ActorLogging{
             def receive={
               case "test" => log.info("received test")
               case _ => log.info("received unknown message!")
             }

             val system = ActorSystem("MyActorSystem")
             val systemLog = system.log

            //Actor
            val myActor = new MyActor
            val myactor = system.actorOf(Props(myActor),name = "myactor")

            systemLog.info("准备向myactor发送消息！")
            //向myactor发送消息
            myactor!"test"
            myactor!"123"

            //关闭ActorSystem，停止程序运行
            system.hashCode()
       }
}
