package com.jinbiao.manyThreads.control_thread_run;

import java.util.concurrent.Exchanger;

/**
 * @author wangjinbiao
 * @date 2023/2/6 13:43
 * @desc 三个线程按序打印ABC方式9:使用交换器Exchanger
 * 在前面，我们已经用到了常用的并发工具类，其实还有一个不那么常用的并发工具类Exchanger，同样也可以用来解决这道题目。
 * Exchanger是基于ThreadLocal实现的
 * 实现思路:Exchanger用于两个线程在某个节点时进行数据交换，在这道题目里:
  ·线程A执行完之后，和线程B用一个交换器交换state，线程B执行完之后，和线程C用一个交换器交换state
  ·在没有轮到自己执行之前，先进行等待
 * demo后自己的理解:就是线程A通过交换器Exchanger设置一个值，线程B如果通过交换器拿到了线程A的设置的预期值就执行。没拿到就自选
 * 然后线程B再通过交换器Exchanger设置一个值，线程C如果通过交换器拿到了线程B的设置的预期值就执行。没拿到就自选
 */
public class UseExchanger {

  private Exchanger<Integer> exchangerB = new Exchanger<>();

  private Exchanger<Integer> exchangerC = new Exchanger<>();

  private void printA() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + "打印A");
    //等待另一个线程到达此交换点,然后将给定对象传输给它
    exchangerB.exchange(2);
  }

  private void printB() throws InterruptedException {
    //交换
    Integer state = exchangerB.exchange(1);
    //等待
    while (state != 2) {

    }
    //执行
    System.out.println(Thread.currentThread().getName() + "打印B");
    //第二次交换
    exchangerC.exchange(3);
  }

  private void printC() throws InterruptedException {
    //交换
    Integer state = exchangerC.exchange(1);
    //等待
    while (state != 3) {

    }
    //执行
    System.out.println(Thread.currentThread().getName() + "打印C");
  }

  public static void main(String[] args) {
    UseExchanger useExchanger = new UseExchanger();

    new Thread(() -> {
      try {
        useExchanger.printA();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread-A").start();

    new Thread(() -> {
      try {
        useExchanger.printB();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread-B").start();

    new Thread(() -> {

      try {
        useExchanger.printC();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread-C").start();
  }
}
