package com.jinbiao.manyThreads.control_thread_run;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WangJinbiao
 * @date 2023/2/5 14:50
 * @desc 三个线程按序打印ABC方式4:使用 AtomicInteger
 * 实现思路：我们都知道synchronized和lock都属于悲观锁，我们还可以用乐观锁来实现。
 * 在Java里，我们熟悉的原子操作类AtomicInteger就是基于CAS实现的，可以用来保证Integer操作的原子性。
 */
public class UseAtomicInteger {

    private AtomicInteger state = new AtomicInteger(1);

    private void printA() {
        System.out.println(Thread.currentThread().getName() + "打印A");
        state.incrementAndGet();
    }

    private void printB() {
        while (state.get() < 4){
            while(state.get() == 2){
                System.out.println(Thread.currentThread().getName() + "打印B");
                state.incrementAndGet();
            }
        }
    }

    private void printC() {
        while (state.get() < 4){
            while(state.get() == 3){
                System.out.println(Thread.currentThread().getName() + "打印C");
                state.incrementAndGet();
            }
        }

    }

    public static void main(String[] args) {
        UseAtomicInteger useAtomicInteger = new UseAtomicInteger();

        new Thread(() -> useAtomicInteger.printA(), "Thread-A").start();

        new Thread(() -> {
            useAtomicInteger.printB();
        }, "Thread-B").start();

        new Thread(() -> {
            useAtomicInteger.printC();
        }, "Thread-C").start();
    }
}
