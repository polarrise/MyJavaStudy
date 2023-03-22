package com.jinbiao.manyThreads.tulinSchool.juc.lock.readWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author：Jinbiao
 * @Date：2023/3/15 21:03
 * @Desc：
 */
public class ReentrantReadWriteLockDemo {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static void test(){
        readWriteLock.readLock().lock();
        try{

        }finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {

    }
}
