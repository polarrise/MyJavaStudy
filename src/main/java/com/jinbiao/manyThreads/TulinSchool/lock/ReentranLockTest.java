package com.jinbiao.manyThreads.TulinSchool.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangjinbiao
 * @date 2023/2/2 17:21
 * @desc
 */
public class ReentranLockTest implements Runnable{
  ReentrantLock lock = new ReentrantLock();
  @Override
  public void run() {
    get();
  }
  public void get() {
    //第一个断点打再这  state 0->1 由无锁转入有锁
    lock.lock();
    System.out.println(Thread.currentThread().getName());
    System.out.println(Thread.currentThread().getId());
    set();
    //state 由1->0 至此同一线程的锁变成无锁状态
    lock.unlock();
  }

  public void set() {
    //第二个断点打在这 state 1->2
    lock.lock();
    System.out.println(Thread.currentThread().getName());
    System.out.println(Thread.currentThread().getId());
    //2->1
    lock.unlock();
  }
  public static void main(String[] args) {
    ReentranLockTest ss = new ReentranLockTest();
    new Thread(ss).start();
    new Thread(ss).start();
  }
}
