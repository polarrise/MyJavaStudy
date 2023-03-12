package com.jinbiao.manyThreads.tulinSchool.juc.lock.semaphoreDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author：Jinbiao
 * @Date：2023/3/12 15:24
 * @Desc：Semaphore实现服务接口限流
 *
 * Semaphore常见应用场景总结：
 * 1.限流：Semaphone可以用于限制对共享资源的并发访问数量，以控制系统的流量
 * 2.资源池：Semaphore可以用于实现资源池，以维护一组有限的共享资源
 */
@Slf4j
public class SemaphoreDemo {

    /**
     * 同一时刻最多允许两个并发
     */
    private static Semaphore semaphore = new Semaphore(2);

    private static Executor executor = Executors.newFixedThreadPool(10);

    public static String getProductInfo(){
        try{
            semaphore.acquire();
            log.info("请求服务");
            Thread.sleep(2000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }finally {
            semaphore.release();
        }
        return "返回商品详情信息";
    }

    public static String getProductInfo2(){
        if(!semaphore.tryAcquire()){
            log.error("请求被流控了");
            return "请求被流控了";
        }
        try{
            log.info("请求服务");
            Thread.sleep(2000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }finally {
            semaphore.release();
        }
        return "返回商品详情信息";
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                getProductInfo2();
            });
        }
    }
}
