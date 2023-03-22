package com.jinbiao.manyThreads.tulinSchool.juc.lock.currencyContainer;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author：Jinbiao
 * @Date：2023/3/17 20:25
 * @Desc：
 *
 * CopyOnWriteArrayList 的应用场景主要有两个方面：
 * 读多写少的场景
 * 由于 CopyOnWriteArrayList 的读操作不需要加锁，因此它非常适合在读多写少的场景中使用。例如，一个读取频率比写入频率高得多的缓存，使用 CopyOnWriteArrayList 可以提高读取性能，并减少锁竞争的开销。
 * 不需要实时更新的数据
 * 由于 CopyOnWriteArrayList 读取的数据可能不是最新的，因此它适合于不需要实时更新的数据。例如，在日志应用中，为了保证应用的性能，日志记录的操作可能被缓冲，并不是实时写入文件系统，而是在某个时刻批量写入。这种情况下，使用 CopyOnWriteArrayList 可以避免多个线程之间的竞争，提高应用的性能。
 */
public class CopyOnWriteArrayListDemo {

    private static CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    // 模拟初始化的黑名单数据
    static {
        copyOnWriteArrayList.add("ipAddr0");
        copyOnWriteArrayList.add("ipAddr1");
        copyOnWriteArrayList.add("ipAddr2");
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Runnable() {
            public void run() {
                // 模拟接入用时
                try {
                    Thread.sleep(new Random().nextInt(5000));
                } catch (Exception e) {}

                String currentIP = "ipAddr" + new Random().nextInt(6);
                if (copyOnWriteArrayList.contains(currentIP)) {
                    System.out.println(Thread.currentThread().getName() + " IP " + currentIP + "命中黑名单，拒绝接入处理");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " IP " + currentIP + "接入处理...");
            }
        };
        new Thread(task, "请求1").start();
        new Thread(task, "请求2").start();
        new Thread(task, "请求3").start();

        new Thread(new Runnable() {
            public void run() {
                // 模拟用时
                try {
                    Thread.sleep(new Random().nextInt(2000));
                } catch (Exception e) {}

                String newBlackIP = "ipAddr3";
                copyOnWriteArrayList.add(newBlackIP);
                System.out.println(Thread.currentThread().getName() + " 添加了新的非法IP " + newBlackIP);
            }
        }, "IP黑名单更新").start();

        Thread.sleep(10000);
    }
}
