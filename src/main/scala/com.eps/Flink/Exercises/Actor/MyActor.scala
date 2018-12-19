package com.eps.Flink.Exercises.Actor

import akka.actor.Actor
import akka.event.Logging


/**
  * 主要内容：
  * 定义Actor、创建Actor
  * //通过akka.actor.Actor特质并实现receive方法来定义Actor
  * //获取LoggingAdapter,用于日志输出
  * val log = Logging(context.system,this)
  *
  * //实现receive方法，定义Actor的行为逻辑，返回一个偏函数
  * def receive = {
  * case "test" => log.info("received test")
  * case _      => log.info("received unknown message")
  * }
  *
  * //receive方法被定义在Actor当中，方法标签如下：
  * type Receive = PartialFunction[Any, Unit]
  * def receive:Actor.Receive
  * */


class MyActor{



}
