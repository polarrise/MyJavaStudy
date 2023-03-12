package com.jinbiao.manyThreads.tulinSchool.juc.lock.reentrantLockDemo;

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
        Queue queue = new Queue(5);
        //启动生产者线程
        new Thread(new Producer(queue),"生产者线程").start();
        //启动消费者线程
        new Thread(new Customer(queue),"消费者线程").start();
    }

}

class Queue{

    private Object [] items;
    private int size = 0;
    int takeIndex,putIndex;
    private ReentrantLock lock;
    /**
     * 消费者线程阻塞唤醒条件：队列为空阻塞，生产者生成完成唤醒
     */
    public Condition customer_Condition;
    /**
     * 生产者线程阻塞唤醒条件：队列满了阻塞，消费者消费完唤醒
     */
    public Condition producer_Condition;


    public Queue(int capacity) {
        this.items = new Object[capacity];
        lock = new ReentrantLock();
        customer_Condition = lock.newCondition();
        producer_Condition = lock.newCondition();
    }

    //生产者put:
    public void put(Object value) throws Exception{
        lock.lock();
        try {
            //队列已满,生产者阻塞：
            while(size == items.length){
                System.out.println("队列已满,生产者阻塞...");
                producer_Condition.await();
            }
            System.out.println("生产者生产的元素:"+value);
            items[putIndex] = value;
            //循环队列取元素生产：
            if(++putIndex == items.length){
                putIndex = 0;
            }
            size++;
            //生产完成唤醒消费者：
            customer_Condition.signal();
        }finally {
            lock.unlock();
        }
    }

    //消费者take:
    public Object take() throws Exception{
        lock.lock();
        try {
            //队列为空,消费者阻塞：
            if(size == 0){
                customer_Condition.await();
            }
            Object item = items[takeIndex];
            //消费完了，位置元素置为空
            items[takeIndex] = null;
            //循环队列取元素消费：
            if(++takeIndex == items.length){
                takeIndex = 0;
            }
            size--;
            //唤醒生产者生产：
            producer_Condition.signal();
            return item;
        }finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable{

    private Queue queue;

    public Producer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            // 每隔1秒钟轮询生产一次
            while(true){
                Thread.sleep(1000);
                queue.put(new Random().nextInt(1000));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


class Customer implements Runnable{

    private Queue queue;

    public Customer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            // 每隔2秒钟轮询消费一次
            while(true){
                Thread.sleep(2000);
                System.out.println("Customer消费信息："+ queue.take());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
