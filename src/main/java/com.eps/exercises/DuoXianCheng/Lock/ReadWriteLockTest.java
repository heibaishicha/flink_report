package com.eps.exercises.DuoXianCheng.Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @deprecated  java写锁
 * @version 1.0
 *
 * */

public class ReadWriteLockTest {

        private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private Map<String,String> cache = new HashMap<>();

        public String readWriteLockTest(String key){
            String value = null;
            readWriteLock.readLock().lock();
            value = cache.get(key);
            if(value == null){
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                //获取写锁后再次判断对象是否为null,防止数据重复更新
                value = cache.get(key);
                if(value == null){
                    System.out.println(Thread.currentThread().getName());
                    value = "123";
                    cache.put(key,value);
                }
                //释放锁
                readWriteLock.writeLock().lock();
            }
            else {
                readWriteLock.readLock().unlock();
            }
            return value;
        }

        public static void main(String[] args) {
            final ReadWriteLockTest test = new ReadWriteLockTest();
            for(int i = 0; i < 10; i++){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + test.readWriteLockTest("1"));
                    }
                }).start();
            }
        }
}


