package com.jinbiao.manyThreads.tulinSchool.juc.lock.ExchangerDemo;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author：Jinbiao
 * @Date：2023/3/12 18:15
 * @Desc：模拟对账场景
 *
 * 应用场景总结
 * Exchanger 可以用于各种应用场景，具体取决于具体的 Exchanger 实现。常见的场景包括：
 * 数据交换：在多线程环境中，两个线程可以通过 Exchanger 进行数据交换。
 * 数据采集：在数据采集系统中，可以使用 Exchanger 在采集线程和处理线程间进行数据交换。
 */
public class ExchangerDemo2 {
    private static final Exchanger<String> exchanger = new Exchanger();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "12379871924sfkhfksdhfks";
                    exchanger.exchange(A);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "32423423jknjkfsbfj";
                    String A = exchanger.exchange(B);
                    System.out.println("A和B数据是否一致：" + A.equals(B));
                    System.out.println("A= "+A);
                    System.out.println("B= "+B);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.shutdown();

    }
}
