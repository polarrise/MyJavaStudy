package com.jinbiao.manyThreads.control_thread_run;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WangJinbiao
 * @date 2023/2/4 22:51
 * @desc 三个线程按序打印ABC方式2:还可以用lock+state来实现
 * 实现思路：用lock来实现同步+用全局变量state标识改哪个线程执行，不执行就释放锁.
 * 这里也有几个细节要注意：
 * 1.要在循环里获取锁，不然线程可能会在获取到锁之前就终止了   ->不循环获取也成功了，但是别吧。  还有一点要while里面去try获取锁。别写成了try里面去while获取锁，会造成一直循环获取到了锁然后报错！！！
 * 2.要用while，而不是if判断，是否当前线程应该打印输出     ->我之前用的是if没问题，但还是别吧
 * 3.要在finally里释放锁，保证其它的线程能获取到锁
 */
public class LockState {

    private final ReentrantLock lock = new ReentrantLock();

    //1：A执行,2：B执行,3:C执行
    private int state = 1;

    private void printA() {
        //自旋:
        while (state < 4) {
            try {
                //获取锁
                lock.lock();
                //要用while，而不是if判断，是否当前线程应该打印输出
                while (state == 1) {
                    System.out.println(Thread.currentThread().getName() + "打印A");
                    state++;
                }
            } finally {
                //要保证不执行的时候，锁能释放掉
                lock.unlock();
            }
        }
    }

    private void printB() {
        //自旋:
        while (state < 4) {
            try {
                //获取锁
                lock.lock();
                //要用while，而不是if判断，是否当前线程应该打印输出
                while (state == 2) {
                    System.out.println(Thread.currentThread().getName() + "打印A");
                    state++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void printC() {
        //自旋:
        while (state < 4) {
            try {
                //获取锁
                lock.lock();
                //要用while，而不是if判断，是否当前线程应该打印输出
                while (state == 3) {
                    System.out.println(Thread.currentThread().getName() + "打印A");
                    state++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        LockState lockState = new LockState();

        new Thread(() -> lockState.printA(), "Thread-A").start();

        new Thread(() -> {
            lockState.printB();
        }, "Thread-B").start();

        new Thread(() -> {
            lockState.printC();
        }, "Thread-C").start();
    }
}
