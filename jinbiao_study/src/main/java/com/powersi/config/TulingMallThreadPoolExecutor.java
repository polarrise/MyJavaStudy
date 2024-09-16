package com.powersi.config;

import org.springframework.util.ObjectUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TulingMallThreadPoolExecutor {

    private ThreadPoolExecutor pool = null;

    public TulingMallThreadPoolExecutor(String name, int corePoolSize, int maximumPoolSize){
        this.pool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,30L, TimeUnit.MINUTES,new ArrayBlockingQueue<>(8),
                new TulingMallThreadFactory(name),new TulingMallRejectExecutionHandler()
        );
    }

    public ExecutorService getLhrmsThreadPoolExecutor(){
        return this.pool;
    }

    /**
     * 拒绝策略
     */
    private class TulingMallRejectExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程工厂
     */
    private class TulingMallThreadFactory implements ThreadFactory{

        private String name;

        private AtomicInteger count;

        private TulingMallThreadFactory(){
            this.count = new AtomicInteger(0);
        }
        private TulingMallThreadFactory(String name){
            this.count = new AtomicInteger(0);
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName;
            if(ObjectUtils.isEmpty(this.name)){
                threadName = TulingMallThreadPoolExecutor.class.getSimpleName() + this.count.addAndGet(1);
            }else {
                threadName = this.name + this.count.addAndGet(1);
            }
            t.setName(threadName);
            return t;
        }
    }
}
