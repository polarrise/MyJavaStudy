package com.jinbiao.manyThreads.tulinSchool.juc.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author：Jinbiao
 * @Date：2023/3/20 10:37
 * @Desc：延时队列DelayQueue的使用
 * 由于每个订单都有不同的延迟时间，因此它们将会按照延迟时间的顺序被取出。当延迟时间到达时，对应的订单对象将会被从队列中取出，并被处理。
 */
public class DelayQueueExample {
    private static final DelayQueue<Order> delayQueue = new DelayQueue<>();

    //添加三个订单，分别延迟5s、2s、3s
    private static void init(){
        delayQueue.put(new Order("order1", System.currentTimeMillis(), 5000));
        delayQueue.put(new Order("order2", System.currentTimeMillis(), 2000));
        delayQueue.put(new Order("order3", System.currentTimeMillis(), 3000));
    }
    public static void main(String[] args) throws InterruptedException {

        init();
        // 循环取出订单，直到所有订单都被处理完毕
        while (!delayQueue.isEmpty()) {
            /**
             * 检索并删除此队列的头，队列为空时等待，直到此队列上有延迟过期的元素可用@返回此队列的的头
             */
            Order order = delayQueue.take();
            System.out.println("处理订单：" + order.getOrderId());
        }

        // 循环取出订单，直到所有订单都被处理完毕
        while (!delayQueue.isEmpty()) {
            /**
             * 检索并删除此队列的头，队列为空时等待，直到此队列上有延迟过期的元素可用@返回此队列的的头
             */
            Order order = delayQueue.take();
            System.out.println("处理订单：" + order.getOrderId());
        }
    }

    static class Order implements Delayed {
        private String orderId;
        private long createTime;
        private long delayTime;

        public Order(String orderId, long createTime, long delayTime) {
            this.orderId = orderId;
            this.createTime = createTime;
            this.delayTime = delayTime;
        }

        public String getOrderId() {
            return orderId;
        }

        /**
         * 在指定延迟时间后执行
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            long diff = createTime + delayTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long diff = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
            return Long.compare(diff, 0);
        }
    }
}

