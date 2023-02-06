package com.jinbiao.manyThreads.control_thread_run;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wangjinbiao
 * @date 2023/2/6 11:50
 * @desc 三个线程按序打印ABC方式8:使用循环栅栏CyclicBarrier
 *
 * 用到了CountDownLatch，我们应该想到，还有一个功能和它类似的工具类CyclicBarrier。
 * 有的翻译叫同步屏障，我觉得翻译成循环栅栏，更能体现它的功能特性。
 *
 * 结合现实意义:就像是出去旅游，大家不同时间到了景区门口，但是景区疫情限流，先把栅栏拉下来，在景区里的游客走一批，打开栅栏，再放进去一批，走一批，再放进去一批……
 * 这就是CyclicBarrier的两个特性: 栅栏：多个线程相互等待，到齐后再执行特定动作。 循环：所有线程释放后，还能继续复用它
 * 当然，CyclicBarrier的实现其实还是基于lock+condition，多个线程在到达一定条件前await，到达条件后signalAll。

 * 这道题怎么用CyclicBarrier解决呢？
 * 实现思路：线程B和线程C需要使用栅栏等待
 * 为了让B和C也顺序执行，需要用一个状态，来标识应该执行的线程
 *
 */
public class UseCyclicBarrier {

  //创建一个新的｛@code CyclicBarrier｝，当给定数量的参与方（线程）正在等待它时，它将跳闸
  private CyclicBarrier cyclicBarrier = new CyclicBarrier(1);

  //一个状态，来标识应该执行的线程
  private int state = 1;

  private void printA(){
    while (state != 1){

    }
    System.out.println(Thread.currentThread().getName() + "打印A");
    state++;
  }

  private void printB() throws BrokenBarrierException, InterruptedException {
    //在栅栏前等待:
    cyclicBarrier.await();
    while (state !=2){

    }
    System.out.println(Thread.currentThread().getName() + "打印B");
    state++;
  }

  private void printC() throws BrokenBarrierException, InterruptedException {
    //在栅栏前等待:
    cyclicBarrier.await();
    while (state !=3){

    }
    System.out.println(Thread.currentThread().getName() + "打印C");
  }

  public static void main(String[] args) {
    UseCyclicBarrier useCyclicBarrier = new UseCyclicBarrier();

    new Thread(() -> useCyclicBarrier.printA(), "Thread-A").start();

    new Thread(() -> {
      try {
        useCyclicBarrier.printB();
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      }
    }, "Thread-B").start();

    new Thread(() -> {

      try {
        useCyclicBarrier.printC();
      } catch (BrokenBarrierException | InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread-C").start();
  }
}
