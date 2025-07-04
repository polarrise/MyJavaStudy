package com.jinbiao.HelperClass;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: rise
 * @date: 2025-01-10 18:13:57
 * @Description: 线程安全测试
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Slf4j
public class ThreadSecurityTest {

    // 全局共享变量，用于模拟线程安全问题
    private static int sharedCounter = 0;

    public static void main(String[] args) throws InterruptedException {

        try {
            String str = null;
            str.length();  // 这里会抛出 NullPointerException
        } catch (Exception e) {
            log.error("An error occurred:", e);
        }


        // 线程列表
        List<Thread> threads = new ArrayList<>();

        List<Integer> result = Collections.synchronizedList(new ArrayList<>());  // 0.4min  读少写多使用Collections.synchronizedList

        // List<Integer> result = new CopyOnWriteArrayList<>();  0.75min  读多写少使用CopyOnWriteArrayList

        long startTime = System.currentTimeMillis();

        // 创建 50 个线程，每个线程往 result 列表中添加不同的数字
        for (int i = 1; i <= 100; i++) {
            Thread thread = new Thread(() -> {
                // 创建一个线程不安全的 List、线程内部的list、非共享list可以使用ArrayList
                List<Integer> subList = new ArrayList<>();
                // 每个线程插入 100 个数字
                for (int j = 1; j <= 100000; j++) {
                    subList.add(getValue());
                }
                // 多线程共享list需要使用线程安全的list
                result.addAll(subList);
            });
            threads.add(thread);
            thread.start();
        }

        // 等待所有线程执行完毕
        for (Thread thread : threads) {
            thread.join();
        }

        // 输出结果列表的大小
        System.out.println("结果列表的大小: " + result.size());

        // 输出最后的 sharedCounter 值
        System.out.println("最终共享计数器值: " + sharedCounter);

        System.out.println("耗时:"+(System.currentTimeMillis()-startTime)/100/60.0f+"min");

    }

    /**
     * 多线程调用的方法，修改共享变量 sharedCounter 以模拟线程安全问题
     */
    private static Integer getValue() {
        // 修改共享的静态变量，模拟线程安全问题,
        return sharedCounter++;
    }

}


