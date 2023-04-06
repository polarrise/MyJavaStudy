package com.powersi.task;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author：Jinbiao
 * @Date：2023/4/3 14:11
 * @Desc：
 */

@Component
@Slf4j
public class XiaofaUserTask {

    @Resource
    private RedissonClient redissonClient;

    private final String  cancelUnpaidOrderLock = "cancelUnpaidOrderLock";

    @Scheduled(cron = "0 0/1 * * * *")
    public void cancelUnpaidOrder() {
        log.info("1分执行一次定时任务开启");
        RLock rLock = redissonClient.getLock(cancelUnpaidOrderLock);
        if (rLock.tryLock()) {
            try {
                System.out.println("");
                return;
            } catch (Exception e) {
                log.error("执行取消未支付订单异常：", e);
                return;
            } finally {
                rLock.unlock();
            }
        }
        log.info("【取消未支付订单】定时任务未获取到锁，无需处理!");
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void cancelUnpaidOrder2() {
        log.info("1分执行一次定时任务开启");
        RLock rLock = redissonClient.getLock(cancelUnpaidOrderLock);
        if (rLock.tryLock()) {
            try {
                System.out.println("");
                return;
            } catch (Exception e) {
                log.error("执行取消未支付订单异常：", e);
                return;
            } finally {
                rLock.unlock();
            }
        }
        log.info("【取消未支付订单】定时任务未获取到锁，无需处理!");
    }
}
