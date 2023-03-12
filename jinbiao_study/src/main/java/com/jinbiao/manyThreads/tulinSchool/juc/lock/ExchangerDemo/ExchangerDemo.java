package com.jinbiao.manyThreads.tulinSchool.juc.lock.ExchangerDemo;

import java.util.concurrent.Exchanger;

/**
 * @Author：Jinbiao
 * @Date：2023/3/12 18:13
 * @Desc：模拟交易场景
 * 用一个简单的例子来看下Exchanger的具体使用。两方做交易，如果一方先到要等另一方也到了才能交易，交易就是执行exchange方法交换数据。
 *
 * 应用场景总结
 * Exchanger 可以用于各种应用场景，具体取决于具体的 Exchanger 实现。常见的场景包括：
 * 数据交换：在多线程环境中，两个线程可以通过 Exchanger 进行数据交换。
 * 数据采集：在数据采集系统中，可以使用 Exchanger 在采集线程和处理线程间进行数据交换。
 */
public class ExchangerDemo {
    private static Exchanger exchanger = new Exchanger();
    static String goods = "电脑";
    static String money = "$4000";
    public static void main(String[] args) throws InterruptedException {

        System.out.println("准备交易，一手交钱一手交货...");
        // 卖家
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("卖家到了，已经准备好货：" + goods);
                try {
                    String money = (String) exchanger.exchange(goods);
                    System.out.println("卖家收到钱：" + money);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(3000);

        // 买家
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("买家到了，已经准备好钱：" + money);
                    String goods = (String) exchanger.exchange(money);
                    System.out.println("买家收到货：" + goods);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
