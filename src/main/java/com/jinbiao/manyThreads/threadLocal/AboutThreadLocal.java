package com.jinbiao.manyThreads.threadLocal;

/**
 * @author wangjinbiao
 * @date 2023/2/7 16:08
 * @desc threadLocal的使用场景：线程里面跨方法的调用可以使用
 */
public class AboutThreadLocal {

  private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

  private Object object = new Object();

  private int state = 1;

  public void setName(String name){
    threadLocal.set(name);
  }

  public String getName(){
    return threadLocal.get();
  }

  public void testA() throws InterruptedException {
      synchronized (object){
        System.out.println("线程A执行了--");
        System.out.println(threadLocal.get());
        threadLocal.set("jinbiao1");
        System.out.println(threadLocal.get());
        //清除Entry对象,避免内存泄露问题
        threadLocal.remove();
        System.out.println("清除完Entry对象后:"+threadLocal.get());
        state++;
        object.notify();
      }
  }

  public void testB() throws InterruptedException {
    synchronized (object){
      System.out.println("线程B执行了--");
      while (state !=2){
        object.wait();
      }
      System.out.println(threadLocal.get());
      threadLocal.set("jinbiao2");
      System.out.println(threadLocal.get());
      //清除Entry对象,避免内存泄露问题
      threadLocal.remove();
      System.out.println("清除完Entry对象后:"+threadLocal.get());
      state++;
      object.notify();
    }
  }

  public static void main(String[] args) {
    AboutThreadLocal aboutThreadLocal = new AboutThreadLocal();

    Thread threadA = new Thread(() ->{
      try {
        aboutThreadLocal.testA();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread-A");
    threadA.start();

    Thread threadB = new Thread(() ->{
      try {
        aboutThreadLocal.testB();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread-B");
    threadB.start();
  }
}
