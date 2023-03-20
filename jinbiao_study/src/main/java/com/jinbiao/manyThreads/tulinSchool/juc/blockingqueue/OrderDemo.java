package com.jinbiao.manyThreads.tulinSchool.juc.blockingqueue;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author：Jinbiao
 * @Date：2023/3/20 11:16
 * @Desc：由于每个订单都有不同的延迟时间，因此它们将会按照延迟时间的顺序被取出。当延迟时间到达时，对应的订单对象将会被从队列中取出，并被处理。
 */
public class OrderDemo {
    private static final DelayQueue<Order> delayQueue = new DelayQueue<>();

    private static void init(){
        //订单延时5s消费
        ZonedDateTime expireTime = ZonedDateTime.now(ZoneId.of("UTC")).plus(3, ChronoUnit.SECONDS);
        delayQueue.put(new Order("1", ZonedDateTime.now(ZoneId.of("UTC")).plus(1, ChronoUnit.SECONDS)));
        delayQueue.put(new Order("2", ZonedDateTime.now(ZoneId.of("UTC")).plus(2, ChronoUnit.SECONDS)));
        delayQueue.put(new Order("3", expireTime));
    }
    public static void main(String[] args) throws InterruptedException {
        init();
        //阻塞
        while (true){
            Order orderFromQueue = delayQueue.take();
            if (orderFromQueue != null) {
                // 从数据库查询订单的支付状态
                System.out.println("订单延时消费：" + orderFromQueue.getOrderId());
            }
        }
    }

    static class Order implements Delayed {
        private String orderId;
        private ZonedDateTime expireTime;

        public Order(String orderId, ZonedDateTime expireTime) {
            this.orderId = orderId;
            this.expireTime = expireTime;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public ZonedDateTime getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(ZonedDateTime expireTime) {
            this.expireTime = expireTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long delay = unit.convert(expireTime.toInstant().toEpochMilli() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            return delay;
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
