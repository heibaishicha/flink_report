package com.eps.exercises.DuoXianCheng;

public class MyRunnableTest implements Runnable {

       private int i;

       void print(){
           System.out.println(Thread.currentThread().getName() + "" + i);
       }

       //run方法同样是线程执行体
       @Override
       public void run(){
           for(; i < 100; i++){
               //当线程类实现Runnable接口时
               //如果想获取当前线程，只能用Thread.currentThread()方法
               print();
           }
       }

       //Test
       public static void main(String[] args) {
           for(int i = 0; i < 100; i++){
               System.out.println(Thread.currentThread().getName() + "" + i);
               if(i == 20){
                   MyRunnableTest st = new MyRunnableTest();
                   //通过new Thread(target, name)方法创建新线程
                   new Thread(st, "新线程-I").start();
                   new Thread(st, "新线程-II").start();

               }
           }
       }
}
