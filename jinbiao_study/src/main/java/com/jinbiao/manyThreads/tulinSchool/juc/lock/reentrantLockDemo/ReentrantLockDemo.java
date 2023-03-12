package com.jinbiao.manyThreads.tulinSchool.juc.lock.reentrantLockDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：Jinbiao
 * @Date：2023/3/10 23:35
 * @Desc：独占锁：模拟抢票场景，
 * 1。不加锁的效果： 出现超卖的问题，总共8张票，10个线程都抢到了票。
 * 2。加锁效果： 正常，两个线程抢票失败。

 * ReentrantLock的应用场景：
 * 1.解决多线程竞争资源的问题，例如多个线程同时对同一个数据库进行写操作，可以使用ReentrantLock保证每次只有一个线程能够写入
 * 2.实现多线程任务的顺序执行，例如一个线程执行完某任务后，再让另一个线程执行任务
 * 3.实现多线程等待、通知机制，例如在某个线程执行完某个任务后，通知其他线程继续执行任务。
 */
public class ReentrantLockDemo {

    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 总票数
     */
    private static int tickets = 8;

    private void buyTicket(){
        lock.lock();
        try{
            if(tickets>0){
                try{
                    //模拟抢票做的一些逻辑：
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+"购买了第"+tickets--+"张票");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                System.out.println("票已经卖完了==");
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                reentrantLockDemo.buyTicket();
            },"线程:"+i).start();
        }

        Thread.sleep(3000);
        System.out.println("剩余"+tickets+"张票");
    }
}

