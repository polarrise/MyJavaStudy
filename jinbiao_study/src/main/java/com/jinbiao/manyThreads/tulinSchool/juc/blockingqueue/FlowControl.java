package com.jinbiao.manyThreads.tulinSchool.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author：Jinbiao
 * @Date：2023/3/19 23:10
 * @Desc：了解阻塞队列ArrayBlockingQueue
 */
public class FlowControl {

    private static final int MAX_REQUESTS = 10; // 最大请求数量
    private static final int MAX_WAIT_TIME = 100; // 最大等待时间（毫秒）
    private static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(MAX_REQUESTS); // 请求队列

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 处理请求
                handleRequest();
            }
        }).start();

        // 模拟 200 次请求
        for (int i = 0; i < 10; i++) {
            // 队列已满，等待一段时间再重新尝试
            if (queue.size() >= MAX_REQUESTS) {
                TimeUnit.MILLISECONDS.sleep(MAX_WAIT_TIME);
            }
            // 将请求任务添加到队列
            queue.offer("requestId:"+i);

        }

    }

    private static void handleRequest() {
        while (true){
            // 获取队列中的请求
            Object request = queue.poll();
            if (request != null) {
                // 处理请求
                System.out.println("处理请求：" + request.toString());
                // 模拟处理时间
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
