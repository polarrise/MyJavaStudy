package com.jinbiao.manyThreads.control_thread_run;

/**
 * @author WangJinbiao
 * @date 2023/2/7 21:27
 * @desc 三个线程按序打印ABC方式13:使用Thread.join()方法。
 *
 * 使用Thread.join()执行后会让线程进入到阻塞状态，主线程中调用线程A的join()方法，会让主线程进入到阻塞队列直到，直到线程A结束或中断线程。
 * 哪个线程调用join方法，它就强占cpu资源，不会再释放,直至执行结束
 */
public class UseThreadJoin {

    private void printA(){
        System.out.println(Thread.currentThread().getName() + "打印A");
    }

    private void printB(){
        System.out.println(Thread.currentThread().getName() + "打印B");
    }

    private void printC(){
        System.out.println(Thread.currentThread().getName() + "打印C");
    }

    public static void main(String[] args){
        UseThreadJoin useThreadJoin = new UseThreadJoin();

        Thread threadA = new Thread(() -> useThreadJoin.printA(), "Thread-A");

        Thread threadB = new Thread(() -> {
            try {
                threadA.join();
                useThreadJoin.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-B");

        Thread threadC = new Thread(() -> {
            try {
                threadB.join();
                useThreadJoin.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-C");
        threadA.start();
        threadB.start();
        threadC.start();

    }
}
