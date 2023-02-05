package com.jinbiao.manyThreads.control_thread_run;

/**
 * @author WangJinbiao
 * @date 2023/2/4 21:55
 * @desc 通过synchronized+wait+notify
 * 实现思路:每个线程都首先尝试去获取对象锁，A执行完通知BC执行，B执行完通知C。
 * 在此期间如果BC先抢去了锁，则调用wait()方法，由运行状态->等待状态。  B等待A执行完。 C等待B执行完
 */
public class SynchronizedWaitNotify {

    //锁住的对象
    private final Object lock = new Object();

    //A线程是否执行过了
    private boolean aExcuted = false;

    //B线程是否执行过了
    private boolean bExcuted = false;

    private void printA(){
        /**
         * IllegalMonitorStateException异常发生是由于程序员没有注意notify()，notify()，wait()方法的使用条件，没有真正理解线程同步机制。
         * 如果当前的线程不是此对象锁的所有者，却调用该对象的notify()，notify()，wait()方法时抛出该异常。
         * 简述:只有获取到锁对象的线程才能够调用该对象的notify()，notify()，wait()方法
         */
        synchronized (lock){
            System.out.println(Thread.currentThread().getName()+"打印A");
            aExcuted = true;
            //A获取到锁后唤醒等待的线程B、C
            lock.notifyAll();
        }
    }

    private void printB() throws InterruptedException {
        synchronized (lock){
            while (!aExcuted){
                lock.wait();
            }
            System.out.println(Thread.currentThread().getName()+"打印B");
            bExcuted = true;
            //B获取到锁后唤醒等待的线程C
            lock.notifyAll();
        }
    }

    private void printC() throws InterruptedException {
        synchronized (lock){
            while (!bExcuted){
                lock.wait();
            }
            System.out.println(Thread.currentThread().getName()+"打印C");
            //A获取到锁后唤醒等待的线程B、C
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        SynchronizedWaitNotify synchronizedWaitNotify = new SynchronizedWaitNotify();

        new Thread(()->synchronizedWaitNotify.printA(),"Thread-A").start();

        new Thread(()-> {
            try {
                synchronizedWaitNotify.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread-B").start();

         new Thread(()-> {
            try {
                synchronizedWaitNotify.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Thread-C").start();
    }




}
