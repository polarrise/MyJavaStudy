package com.jinbiao.manyThreads.control_thread_run;

/**
 * @author WangJinbiao
 * @date 2023/2/4 23:47
 * @desc 直接用volatile修饰变量，volatile能保证变量的更改对所有线程可见。
 */
public class UseVolatile {

    /**
     * 判断是否执行：1表示应该A执行，2表示应该B执行，3表示应该C执行
     */
    private static volatile Integer state = 1;

    private void printA() {
        while(state == 1){
            System.out.println(Thread.currentThread().getName() + "打印A");
            state++;
        }
    }

    private void printB() {
        while(state == 2){
            System.out.println(Thread.currentThread().getName() + "打印B");
            state++;
        }
    }

    private void printC() {
        while(state == 3){
            System.out.println(Thread.currentThread().getName() + "打印C");
            break;
        }
    }

    public static void main(String[] args) {
        UseVolatile useVolatile = new UseVolatile();

        new Thread(() -> useVolatile.printA(), "Thread-A").start();

        new Thread(() -> {
            useVolatile.printB();
        }, "Thread-B").start();

        new Thread(() -> {
            useVolatile.printC();
        }, "Thread-C").start();
    }
}
