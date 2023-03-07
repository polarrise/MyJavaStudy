package com.jinbiao.manyThreads.control_thread_run;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangjinbiao
 * @date 2023/2/6 9:43
 * @desc 三个线程按序打印ABC方式5:lock+condition
 * 在Java中，除了Object的wait和notify/notify可以实现等待/通知机制，Condition和Lock配合同样可以完成等待通知机制。
 * 实现思路：使用condition.await()，使当前线程进入等待状态，使用condition.signal()或者condition.signalAll()唤醒等待线程。
 * Condition类的await()、signal()和signalAll()，一般是配合Lock一起使用，是显式的线程间协调同步操作类。
 */
public class LockCondition {

  //可重入锁
  private Lock lock = new ReentrantLock();

  //判断是否执行：1表示应该A执行，2表示应该B执行，3表示应该C执行
  private int state = 1;

  //condition对象
  private Condition a =lock.newCondition();
  private Condition b =lock.newCondition();
  private Condition c =lock.newCondition();

  private void printA(){
    while (state < 4){
      try{
        lock.lock();
        while (state != 1){
          a.await();
        }
        System.out.println(Thread.currentThread().getName() + "打印A");
        state++;
        //a.signalAll();
        b.signal();
      }catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }
  }

  private void printB() throws InterruptedException {
    while (state < 4){
      try{
        lock.lock();
        while (state != 2){
          //使当前线程进入等待状态
          b.await();
        }
        System.out.println(Thread.currentThread().getName() + "打印B");
        state++;
        //b.signalAll();
        c.signal();
      } finally {
        lock.unlock();
      }
    }
  }

  private void printC() throws InterruptedException {
    while (state < 4){
      try{
        lock.lock();
        while (state != 3){
          //使当前线程进入等待状态
          c.await();
        }
        System.out.println(Thread.currentThread().getName() + "打印C");
        state++;
      } finally {
        lock.unlock();
      }
    }
  }

  public static void main(String[] args) {
    LockCondition lockCondition = new LockCondition();

    new Thread(()-> {

        lockCondition.printA();

    },"Thread-A").start();

    new Thread(()-> {
      try {
        lockCondition.printB();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    },"Thread-B").start();

    new Thread(()-> {
      try {
        lockCondition.printC();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    },"Thread-C").start();
  }
}
