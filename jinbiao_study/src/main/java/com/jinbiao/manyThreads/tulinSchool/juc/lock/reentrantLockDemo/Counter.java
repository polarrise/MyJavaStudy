package com.jinbiao.manyThreads.tulinSchool.juc.lock.reentrantLockDemo;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：Jinbiao
 * @Date：2023/3/11 0:11
 * @Desc：可重入锁
 * 可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，再进入该线程的内层方法会自动获取锁（前提锁对象得是同一个对象），不会因为之前已经获取过还没释放而阻塞。
 * Java中ReentrantLock和synchronized都是可重入锁，可重入锁的一个优点是可一定程度避免死锁。在实际开发中，可重入锁常常应用于递归操作、调用同一个类中的其他方法、锁嵌套等场景中。
 *
 * ReentrantLock的应用场景：
 * 1.解决多线程竞争资源的问题，例如多个线程同时对同一个数据库进行写操作，可以使用ReentrantLock保证每次只有一个线程能够写入
 * 2.实现多线程任务的顺序执行，例如一个线程执行完某任务后，再让另一个线程执行任务
 * 3.实现多线程等待、通知机制，例如在某个线程执行完某个任务后，通知其他线程继续执行任务。
 */
public class Counter {

    private final ReentrantLock lock = new ReentrantLock();

    public void recursiveCall(int num) {
        lock.lock();
        try {
            if(num == 0){
                return;
            }
            System.out.println("执行递归,num="+num);
            recursiveCall(num-1);
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.recursiveCall(10);
    }
}
