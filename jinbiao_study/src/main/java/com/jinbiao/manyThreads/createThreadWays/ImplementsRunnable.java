package com.jinbiao.manyThreads.createThreadWays;

/**
 * @author wangjinbiao
 * @date 2023/1/17$ 10:16$
 * @desc
 *实现Runnable接口,重写run方法,创建线程对象,开启线程
 */
public class ImplementsRunnable implements Runnable{
  @Override
  public void run() {
    System.out.println("创建线程方式--实现Runnable接口--");
  }

  public static void main(String[] args) {
    ImplementsRunnable runnable = new ImplementsRunnable();
    /**
     * 当线程开启，runnable的run方法将被调用
     */
    Thread thread = new Thread(runnable);
    thread.start();
  }
}
