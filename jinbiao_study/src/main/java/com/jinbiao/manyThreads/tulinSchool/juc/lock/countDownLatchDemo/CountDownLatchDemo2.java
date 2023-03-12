package com.jinbiao.manyThreads.tulinSchool.juc.lock.countDownLatchDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author：Jinbiao
 * @Date：2023/3/12 16:27
 * @Desc：多任务完成后合并汇总
 * 很多时候，我们的并发任务，存在前后依赖关系；比如数据详情页需要同时调用多个接口获取数据，
 * 并发请求获取到数据后、需要进行结果合并；或者多个数据操作完成后，需要数据check。
 *
 * 应用场景总结:
 * 1.并行任务同步：CountDownLatch可以用于协调多个并行任务的完全情况，确保所有任务都完成后再继续执行下一步操作
 * 2.多任务汇总:CountDownLatch可以用于统计多个线程的完成情况，以确定所有线程都已完成工作
 * 3.资源初始化：CountDownLatch可以用于等待资源的初始化完成，以便在资源初始化完成后开始使用
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(()->{
                try{
                    Thread.sleep(1000+ ThreadLocalRandom.current().nextInt(2000));
                    System.out.println("任务"+index+"执行完成");
                    countDownLatch.countDown();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }).start();
        }

        //主线程阻塞，当计数器为0，唤醒主线程往下执行
        countDownLatch.await();
        System.out.println("主线程:在所有任务运行完成后，进行结果汇总");
    }
}
