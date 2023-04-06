package com.jinbiao.manyThreads.tulinSchool.threadPool.ForkJoinPoolTest;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @Author：Jinbiao
 * @Date：2023/3/26 20:52
 * @Desc：
 */
public class ForkJoinThreadPoolTest {
    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 无返回值的任务
        RecursiveActionTaskTest recursiveActionTaskTest = new RecursiveActionTaskTest();

        // 有返回值的任务
        RecursiveTaskTest recursiveTaskTest = new RecursiveTaskTest();

        recursiveActionTaskTest.fork();
        recursiveActionTaskTest.join();
        //forkJoinPool.invokeAll(recursiveActionTaskTest,recursiveTaskTest);
        forkJoinPool.invoke(recursiveActionTaskTest);
    }
}

class RecursiveActionTaskTest extends RecursiveAction{

    @Override
    protected void compute() {

    }
}

class RecursiveTaskTest extends RecursiveTask{

    @Override
    protected Object compute() {
        return null;
    }
}
