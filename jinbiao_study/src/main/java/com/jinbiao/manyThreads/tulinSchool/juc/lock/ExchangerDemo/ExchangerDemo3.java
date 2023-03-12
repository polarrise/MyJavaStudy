package com.jinbiao.manyThreads.tulinSchool.juc.lock.ExchangerDemo;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Exchanger;

/**
 * @Author：Jinbiao
 * @Date：2023/3/12 18:15
 * @Desc：模拟队列中交换数据场景
 *
 * 应用场景总结
 * Exchanger 可以用于各种应用场景，具体取决于具体的 Exchanger 实现。常见的场景包括：
 * 数据交换：在多线程环境中，两个线程可以通过 Exchanger 进行数据交换。
 * 数据采集：在数据采集系统中，可以使用 Exchanger 在采集线程和处理线程间进行数据交换。
 */
public class ExchangerDemo3 {

    private static ArrayBlockingQueue<String> fullQueue = new ArrayBlockingQueue<>(5);
    private static ArrayBlockingQueue<String> emptyQueue = new ArrayBlockingQueue<>(5);
    private static Exchanger<ArrayBlockingQueue<String>> exchanger = new Exchanger<>();


    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();

    }

    /**
     * 生产者
     */
    static class Producer implements Runnable {
        @Override
        public void run() {
            ArrayBlockingQueue<String> current = emptyQueue;
            try {
                while (current != null) {
                    String str = UUID.randomUUID().toString();
                    try {
                        current.add(str);
                        System.out.println("producer：生产了一个序列：" + str + ">>>>>加入到交换区");
                        Thread.sleep(2000);
                    } catch (IllegalStateException e) {
                        System.out.println("producer：队列已满，换一个空的");
                        current = exchanger.exchange(current);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费者
     */
    static class Consumer implements Runnable {
        @Override
        public void run() {
            ArrayBlockingQueue<String> current = fullQueue;
            try {
                while (current != null) {
                    if (!current.isEmpty()) {
                        String str = current.poll();
                        System.out.println("consumer：消耗一个序列：" + str);
                        Thread.sleep(1000);
                    } else {
                        System.out.println("consumer：队列空了，换个满的");
                        current = exchanger.exchange(current);
                        System.out.println("consumer：换满的成功~~~~~~~~~~~~~~~~~~~~~~");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
