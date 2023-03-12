package com.jinbiao.manyThreads.tulinSchool.juc.lock.countDownLatchDemo;

import java.util.concurrent.CountDownLatch;

/**
 * @Author：Jinbiao
 * @Date：2023/3/12 16:11
 * @Desc：模拟实现百米赛跑
 *
 * 应用场景总结:
 * 1.并行任务同步：CountDownLatch可以用于协调多个并行任务的完全情况，确保所有任务都完成后再继续执行下一步操作
 * 2.多任务汇总:CountDownLatch可以用于统计多个线程的完成情况，以确定所有线程都已完成工作
 * 3.资源初始化：CountDownLatch可以用于等待资源的初始化完成，以便在资源初始化完成后开始使用
 */
public class CountDownLatchDemo {
    //begin 代表裁判初始化为1
    private static CountDownLatch begin = new CountDownLatch(1);

    // end 代表玩家 初始化为8
    private static CountDownLatch end = new CountDownLatch(8);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 8; i++) {
            new Thread(()->{
                try {
                    //预备状态：
                    System.out.println("参赛者:"+Thread.currentThread().getName()+"已经准备好了");
                    //等待裁判吹哨：
                    begin.await();
                    //开始跑步：
                    System.out.println("参赛者:"+Thread.currentThread().getName()+"开始跑步");
                    Thread.sleep(3000);
                    //跑步结束,跑完了
                    System.out.println("参赛者"+Thread.currentThread().getName()+"到达终点");
                    //跑到终点，计数器减一
                    end.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(3000);
        // 倒数5-1，裁判开始吹哨：
        for (int i = 5; i >=1; i--) {
            System.out.println(Thread.currentThread().getName()+"倒数："+i);
            Thread.sleep(1000);
        }
        System.out.println("裁判吹哨，开始比赛===");
        begin.countDown();

        //等待所有玩家到达终点：
        end.await();
        System.out.println("比赛结束===");
    }
}
