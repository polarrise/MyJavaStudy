package com.jinbiao.manyThreads.tulinSchool.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author：Jinbiao
 * @Date：2023/3/19 23:23
 * @Desc：
 * 生产者-消费者模型
 * 在生产者-消费者模型中，生产者向队列中添加元素，消费者从队列中取出元素进行处理。阻塞队列可以很好地解决生产者和消费者之间的并发问题，避免线程间的竞争和冲突。
 */
public class BlockingQueueExample {

    private static final int QUEUE_CAPACITY = 5;
    private static final int PRODUCER_DELAY_MS = 1000;
    private static final int CONSUMER_DELAY_MS = 2000;

    public static void main(String[] args) {
        // 创建一个容量为QUEUE_CAPACITY的阻塞队列
        BlockingQueue<String> queue =new ArrayBlockingQueue<>(QUEUE_CAPACITY);

        new Thread(()->{
            for(;;){
                try{
                    queue.put("pdoducer");
                    System.out.println("生产了一个元素，队列中元素个数:"+queue.size());
                    Thread.sleep(PRODUCER_DELAY_MS);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"生产者线程").start();

        new Thread(()->{
            for(;;){
                try{
                    //队列为空时阻塞:
                    String element = queue.take();
                    System.out.println("消费了一个元素，"+element+"队列中元素个数:"+queue.size());
                    Thread.sleep(CONSUMER_DELAY_MS);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"消费者线程").start();
    }

}
