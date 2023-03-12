package com.jinbiao.manyThreads.tulinSchool.juc.lock.reentrantLockDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：Jinbiao
 * @Date：2023/3/12 18:48
 * @Desc：
 */
public class SyncDemo {
    private static int counter = 0;
    private final static Lock lock = new ReentrantLock();

    public static void increment() {
        lock.lock();
        //synchronized (SyncDemo.class){
        try {
            counter++;
        }finally {
            lock.unlock();
        }

        //}
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                increment();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                increment();
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        //思考： counter=？
        System.out.println(counter);
    }
}
