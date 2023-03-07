package com.jinbiao.manyThreads.control_thread_run;

import java.util.concurrent.Semaphore;

/**
 * @author wangjinbiao
 * @date 2023/2/6 11:23
 * @desc 三个线程按序打印ABC方式6:使用信号量Semaphore
 * 实现思路：线程间同步，还可以使用信号量Semaphore，信号量顾名思义，多线程协作时完成信号传递。
 * 使用acquire()获取许可，如果没有可用的许可，线程进入阻塞等待状态；使用release释放许可。

 * 结合现实意义：Semaphore就相当于是红绿灯
 * 1.信号量释放许可证->相当于绿灯亮起放行所有车子(线程)。  semaphoreB.release()
 * 2.然后路上的车子看见是绿灯了->也就是获取到了许可证 semaphoreB.acquire()
 */
public class UseSemaphore {

  private Semaphore semaphoreB = new Semaphore(0);

  private Semaphore semaphoreC = new Semaphore(0);

  private void printA(){
    System.out.println(Thread.currentThread().getName() + "打印A");
    /**
     * 释放许可证，将其返回信号灯<p> 发布许可证，将可用许可证数量增加一个。
     * 如果任何线程正在尝试获取许可，那么将选择一个线程并给予刚刚释放的许可
     * 自己理解:把该交通信号灯打开(开个绿灯)--如果某个线程拿到了这个许可证(semaphoreB)可直接通行
     */
    semaphoreB.release();
  }

  private void printB() throws InterruptedException {
    /**
     * 从这个信号量获取许可证，阻塞直到一个可用
     * 如果许可证可用，则获取许可证并立即返回，将可用许可证数量减少一个
     * 自己理解:线程B拿到了这个许可证(semaphoreB)可直接通行
     */
    semaphoreB.acquire();
    System.out.println(Thread.currentThread().getName() + "打印B");
    semaphoreC.release();
  }

  private void printC() throws InterruptedException {
    semaphoreC.acquire();
    System.out.println(Thread.currentThread().getName() + "打印C");
  }

  public static void main(String[] args) {
    UseSemaphore useSemaphore = new UseSemaphore();

    new Thread(() -> useSemaphore.printA(), "Thread-A").start();

    new Thread(() -> {
      try {
        useSemaphore.printB();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread-B").start();

    new Thread(() -> {
      try {
        useSemaphore.printC();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread-C").start();
  }
}
