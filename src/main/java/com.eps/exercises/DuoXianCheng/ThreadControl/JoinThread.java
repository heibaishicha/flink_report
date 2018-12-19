package com.eps.exercises.DuoXianCheng.ThreadControl;

public class JoinThread extends Thread {

    //提供一个有参数的构造器，用于设置该线程的名字
    public JoinThread(String name){
        super(name);
    }

    //重写run方法，定义线程执行体
    public void run(){
         for(int i = 0; i < 100; i++){
             System.out.println(getName() + "" + i);
         }
    }

    //Test
    public static void main(String[] args) {
        //启动子线程
        new JoinThread("新线程：").start();
        for(int i = 0; i < 100; i++){
            if(i == 20){
                try {
                    JoinThread jt = new JoinThread("被Join的线程：");
                    jt.start();
                    //main线程调用了jt线程的join()方法，main线程必须等待jt执行结束才会向下执行
                    jt.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "" + i);
        }
    }

}
