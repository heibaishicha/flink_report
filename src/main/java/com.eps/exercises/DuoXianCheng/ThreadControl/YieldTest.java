package com.eps.exercises.DuoXianCheng.ThreadControl;

public class YieldTest extends Thread {

       public YieldTest(String name){
           super(name);
       }

       //定义run方法作为线程执行体
       public void run(){
           for(int i = 0; i < 50; i++){
               System.out.println(getName() + "" + i);
               //当i=20,使用yiel方法让当前线程让步
               if(i == 20){
                   Thread.yield();
               }
           }
       }

       public static void main(String[] args) {
              //启动两条并发线程
              YieldTest yt1 = new YieldTest("高级");
              //将yt1设置成高级
              //yt1.setPriority(Thread.MAX_PRIORITY);
              yt1.start();
              YieldTest yt2 = new YieldTest("低级");
              //将yt2设置成低级
              //yt2.setPriority(Thread.MIN_PRIORITY);
              yt2.start();
       }

}
