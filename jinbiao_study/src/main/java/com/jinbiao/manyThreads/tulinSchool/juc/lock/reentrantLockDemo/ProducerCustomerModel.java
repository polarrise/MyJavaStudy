package com.jinbiao.manyThreads.tulinSchool.juc.lock.reentrantLockDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：Jinbiao
 * @Date：2023/3/11 0:22
 * @Desc：结合Condition实现生产者消费者模式,基于ReentrantLock和Condition实现一个简单队列，等待通知机制
 * java.util.concurrent类库中提供Condition类来实现线程之间的协调。调用Condition.await() 方法使线程等待，其他线程调用Condition.signal() 或 Condition.signalAll() 方法唤醒等待的线程。
 * 注意：调用Condition的await()和signal()方法，都必须在lock保护之内。
 *
 * ReentrantLock的应用场景：
 * 1.解决多线程竞争资源的问题，例如多个线程同时对同一个数据库进行写操作，可以使用ReentrantLock保证每次只有一个线程能够写入
 * 2.实现多线程任务的顺序执行，例如一个线程执行完某任务后，再让另一个线程执行任务
 * 3.实现多线程等待、通知机制，例如在某个线程执行完某个任务后，通知其他线程继续执行任务。
 */

public class ProducerCustomerModel {
    public static void main(String[] args) {
        //创建队列长度为5的队列
        MyBlockingQueue queue = new MyBlockingQueue(5);
        //启动生产者线程
        new Thread(new Producer(queue),"生产者线程").start();
        //启动消费者线程
        new Thread(new Customer(queue),"消费者线程").start();
    }

}

/**
 * 数组阻塞队列：
 */
@Slf4j
class MyBlockingQueue{
    private Object [] items;

    // 记录队列里面还剩的元素数量
    private int size = 0;

    // 记录取元素的索引、 存元素的索引
    int takeIndex,putIndex;

    // 存取元素都公用一把互斥锁
    private ReentrantLock lock;

    /**
     * 消费者线程阻塞唤醒条件：队列为空阻塞，生产者生成完成唤醒
     */
    public Condition customer_Condition;
    /**
     * 生产者线程阻塞唤醒条件：队列满了阻塞，消费者消费完唤醒
     */
    public Condition producer_Condition;


    public MyBlockingQueue(int capacity) {
        this.items = new Object[capacity];
        lock = new ReentrantLock();
        customer_Condition = lock.newCondition();
        producer_Condition = lock.newCondition();
    }

    //生产者put:
    public void put(Object value) throws Exception{
        lock.lock();
        try {
            //队列已满,生产者阻塞等待：
            while(size == items.length){
                log.info("队列已满,生产者阻塞...");
                producer_Condition.await();
            }
            items[putIndex] = value;
            log.info("生产者生产的元素:{},生产元素位置:{}",value,putIndex);
            //循环队列取元素生产：如果生产元素的位置到了数组末尾，则又从数组0号索引位置开始存放
            if (++putIndex == items.length){
                putIndex = 0;
            }
            // 生产完,数组里面的元素数量+1
            size++;
            //生产完成唤醒消费者：
            customer_Condition.signal();
        }finally {
            lock.unlock();
        }
    }

    //消费者take:
    public Object take() throws Exception {
        lock.lock();
        try {
            //队列为空,消费者阻塞：
            if(size == 0){
                customer_Condition.await();
            }
            Object item = items[takeIndex];
            log.info("消费者消费的元素:{},消费元素位置:{}",item,takeIndex);

            //消费完了，位置元素置为空
            items[takeIndex] = null;

            //循环队列取元素消费：如果消费的位置到了数组末尾，则又从数组0号索引位置开始消费
            if(++takeIndex == items.length){
                takeIndex = 0;
            }
            // 消费完,数组里面的元素数量-1
            size--;
            //唤醒生产者生产：
            producer_Condition.signal();
            return item;
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 生产者：1s生产一次
 */
class Producer implements Runnable{

    private MyBlockingQueue queue;

    public Producer(MyBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            // 每隔1秒钟轮询生产一次
            while(true){
                Thread.sleep(2000);
                queue.put(new Random().nextInt(1000));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

/**
 * 消费者：2s消费一次
 */
class Customer implements Runnable{

    private MyBlockingQueue queue;

    public Customer(MyBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            // 每隔2秒钟轮询消费一次
            while(true){
                Thread.sleep(1000);
                System.out.println("Customer消费信息："+ queue.take());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
