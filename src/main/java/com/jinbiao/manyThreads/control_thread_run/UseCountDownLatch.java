package com.jinbiao.manyThreads.control_thread_run;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangjinbiao
 * @date 2023/2/6 11:39
 * @desc 三个线程按序打印ABC方式7:使用计数器CountDownLatch
 * CountDownLatch的一个适用场景，就是用来进行多个线程的同步管理，
 * 线程调用了countDownLatch.await()之后，需要等待countDownLatch的信号countDownLatch.countDown()，在收到信号前，它不会往下执行。
 */
public class UseCountDownLatch {

  private CountDownLatch countDownLatchB = new CountDownLatch(1);

  private CountDownLatch countDownLatchC = new CountDownLatch(1);

  private void printA(){
    System.out.println(Thread.currentThread().getName() + "打印A");
    countDownLatchB.countDown();
  }

  private void printB() throws InterruptedException {
    countDownLatchB.await();
    System.out.println(Thread.currentThread().getName() + "打印B");
    countDownLatchC.countDown();
  }

  private void printC() throws InterruptedException {
    countDownLatchC.await();
    System.out.println(Thread.currentThread().getName() + "打印C");
  }

  public static void main(String[] args) {
    UseCountDownLatch useCountDownLatch = new UseCountDownLatch();

    new Thread(() -> useCountDownLatch.printA(), "Thread-A").start();

    new Thread(() -> {
      try {
        useCountDownLatch.printB();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread-B").start();

    new Thread(() -> {
      try {
        useCountDownLatch.printC();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread-C").start();
  }
}
