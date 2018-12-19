package com.eps.exercises.DuoXianCheng;

public class MyThreadTest extends Thread{

    //多线程创建方式：Thread类、Runnable接口、Callable接口
    private int i;

    //重写run,run方法的方法体就是线程执行体
    @Override
    public void run(){
        for(; i<100; i++){
            //当线程类继承Thread类时，直接使用this即可获取当前线程
            //Thread对像的getaName()返回当前线程的名字
            //因此可以直接调用getName()返回当前线程的名字
            System.out.println(getName() + "" +i);
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            //调用Thread的curentThread方法获取当前线程
            System.out.println(Thread.currentThread().getName() + "" + i);
            if(i == 20){
                //创建、并启动第1条线程
                new MyThreadTest().start();
                //创建、并启动第2条线程
                new MyThreadTest().start();
            }
        }
    }
}
