package com.jinbiao.manyThreads.tulinSchool.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author：Jinbiao
 * @Date：2023/3/22 20:47
 * @Desc：
 */
public class ThreadPoolTest {

    /**
     * 测试线程执行任务异常，线程结束了，然后底层addWorker()再新建一个核心线程。
     * 生产一般推荐使用有界的队列
     * 效果演示结果:  ps:核心线程数为1的情况下~
     * 1.如果任务都不抛出异常，那么两个任务由同一个线程"pool-1-thread-1"执行完任务。
     * 2.如果执行第一个任务发生了异常，那么第二个任务执行由线程池创建的新线程"pool-1-thread-2"线程执行
     */
    private static void test1(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        executor.execute(()->{
                System.out.println(Thread.currentThread().getName());
                throw new NullPointerException();
        });

        executor.execute(()->{
                System.out.println(Thread.currentThread().getName());
                throw new NullPointerException();
        });
    }

    /**
     * 测试线程中断：
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("线程休眠10s钟.....");
                for (int i = 10; i > 0; i--) {
                    System.out.println("倒计时....."+i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("线程被中断啦--");
            }
        });
        thread.start();

        // 主线程休眠3s后中断线程:
        System.out.println("主线程休眠3s后提前中断了线程--线程不休眠啦");
        Thread.sleep(3000);
        thread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Runtime.getRuntime().availableProcessors());

        //测试线程中断：
        //test2();

        //测试线程执行任务异常，线程结束了，然后底层addWorker()再新建一个核心线程。
        test1();
     }
}