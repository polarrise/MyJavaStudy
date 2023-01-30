package com.jinbiao.manyThreads.createThreadWays;

/**
 * @author wangjinbiao
 * @date 2023/1/17$ 9:55$
 * @desc
 * 继承线程类，重写run方法,启动线程执行run方法
 */
public class ExtendThread extends Thread{
  @Override  //重写
  public void run() {
    System.out.println("创建线程方式1,继承Thread类，重写run方法, 线程运行成功!");
  }

  public static void main(String[] args) throws InterruptedException {
    System.out.println("主线程运行11--");
    new ExtendThread().start();
  }
}
