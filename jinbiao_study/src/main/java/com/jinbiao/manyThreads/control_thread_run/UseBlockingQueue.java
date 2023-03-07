package com.jinbiao.manyThreads.control_thread_run;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author wangjinbiao
 * @date 2023/2/6 17:06
 * @desc 三个线程按序打印ABC方式12:使用阻塞队列BlockingQueue
 * 实现思路:阻塞队列同样也可以用来进行线程调度。
 * 利用队列的长度，来确定执行者
 * 利用队列的阻塞性，来保证入队操作同步执行。
 *
 * 阻塞队列（BlockingQueue） 是一个支持两个附加操作的队列。
 * 这两个附加的操作是：在队列为空时，获取元素的线程会等待队列变为非空。当队列满时，存储元素的线程会等待队列可用。
 * 阻塞队列常用于生产者和消费者的场景:生产者是往队列里添加元素的线程，消费者是从队列里拿元素的线程。
 * 阻塞队列就是生产者存放元素的容器，而消费者也只从容器里拿元素。
 */
public class UseBlockingQueue {

  private BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

  private void printA(){
    System.out.println(Thread.currentThread().getName() + "打印A");
    /**
     * 如果可以在不违反容量限制的情况下立即将指定元素插入此队列,
     * 则在成功时返回｛@code true｝，如果当前没有可用空间，则返回｛@code false｝
     */
    queue.offer("B");
  }

  private void printB(){
    while (queue.size() != 1){

    }
    //element()方法:返回队列头部，如果为空报异常
    //queue.element();

    //返回头部，队列为空返回null
    //System.out.println(queue.peek());

    queue.stream().forEach(str->{
      if(str.equals("B")){
        System.out.println(Thread.currentThread().getName() + "打印B");
      }
    });
    queue.offer("C");
  }

  private void printC(){
    while (queue.size() != 2){

    }
    queue.stream().forEach(str->{
      if(str.equals("C")){
        System.out.println(Thread.currentThread().getName() + "打印C");
      }
    });
  }

  public static void main(String[] args) {
    UseBlockingQueue useBlockingQueue = new UseBlockingQueue();

    new Thread(()->{
      useBlockingQueue.printA();
    },"Thread-A").start();

    new Thread(()->{
      useBlockingQueue.printB();
    },"Thread-B").start();

    new Thread(()->{
      useBlockingQueue.printC();
    },"Thread-C").start();
  }
}
