package com.eps.exercises.DuoXianCheng;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallableTest implements Callable {

       //实现Call方法，作为线程执行体
       public Integer call(){
           int i = 0;
           for(; i < 100; i++){
               System.out.println(Thread.currentThread().getName()+ "\t" + i);
           }
           return i;
       }

       public static void main(String[] args) {
           //创建Callable对像
           MyCallableTest myCallableTest = new MyCallableTest();
           //使用
           FutureTask<Integer> task = new FutureTask<Integer>(myCallableTest);
           for(int i = 0; i < 100; i++){
               System.out.println(Thread.currentThread().getName() + "\t" + i);
               if(i == 20){
                   //实质还是以Callable对像处理，并启动线程
                   new Thread(task, "Callable").start();
               }
           }
           try{
               //获取线程返回值
               System.out.println("callable返回值：" + task.get());
           }catch (Exception e){
               e.printStackTrace();
           }
       }
}
