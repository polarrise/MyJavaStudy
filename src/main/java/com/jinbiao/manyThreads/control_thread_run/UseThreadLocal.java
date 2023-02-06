package com.jinbiao.manyThreads.control_thread_run;

/**
 * @author wangjinbiao
 * @date 2023/2/6 14:36
 * @desc 三个线程按序打印ABC方式10:使用ThreadLocal

 * 子线程是并发执行的，但是主线程的代码是顺序执行的，我们在主线程里改变变量，子线程根据变量判断。
 * 那么问题来了，子线程怎么获取主线程的变量呢？可以用InheritableThreadLocal。
 */
public class UseThreadLocal {

  public static void main(String[] args) {
    /**
     * 使用ThreadLocal存储变量:
     * InheritableThreadLocal 继承了 ThreadLocal
     * 以提供从父线程到子线程的值继承：当创建子线程时，子线程接收父线程具有值的所有可继承线程本地变量的初始值
     */
    ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
    new Thread(()->{
      System.out.println(Thread.currentThread().getName() + "打印A");
    },"Thread-A").start();
    //设置变量值
    threadLocal.set(1);

    new Thread(()->{
      //等待
      while (!threadLocal.get().equals(1)){

      }
      System.out.println(Thread.currentThread().getName() + "打印B");
    },"Thread-B").start();
    threadLocal.set(2);

    new Thread(() -> {
      while (threadLocal.get() != 2) {
      }
      System.out.println(Thread.currentThread().getName() + "打印C");

    }, "Thread-C").start();
  }
}
