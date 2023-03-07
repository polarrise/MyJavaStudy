package com.jinbiao.manyThreads.createThreadWays;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wangjinbiao
 * @date 2023/1/17 14:47
 * @desc 使用Callable和Future创建线程
 * （参数列表）->{一些重要方法的代码};
 *  ():接口中抽象方法的参数列表，没有参数，就空着；有参数就写出参数，多个参数用逗号分隔。
 *  ->：传递：把参数传递给方法体{}             --接口里面的抽象方法有参,所以 ->之前就一定要写参数
 *  {}:重写接口的抽象方法的方法体              --相当于重写了接口的抽象方法。  只写方法体就好了，方法名省略了
 *  箭头操作符的左侧对应接口中参数列表(lambda表达式的参数列表)，
 *  箭头右侧为该抽象方法的实现（lambda表达式所需执行的功能）。
 */
public class ImplementsCallable implements Callable {
  @Override
  public Object call() throws Exception {
    return "实现Callable接口-- 创建线程(异步任务)";
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ImplementsCallable callable = new ImplementsCallable();

    // 使用Lambda表达式创建Callnable对象, FutureTask实现类实现了Runnable接口的
    FutureTask<String> futureTask1 = new FutureTask(callable);
    FutureTask<Integer> futureTask = new FutureTask(()->5);
    //开启线程
    Thread thread1 =  new Thread(futureTask1,"有返回值的线程");
    Thread thread =  new Thread(futureTask,"有返回值的线程");
    thread.start();
    thread1.start();
    System.out.println(futureTask1.get());
    /**
     *  join()方法的作用就是让主线程等待子线程执行结束之后再运行主线程。
     *  谁调用join方法，谁就强占cpu资源，它就不会再释放,直至执行结束
     *
     *  yield和sleep的区别: 是否随时可能再次被调度. sleep期间, 它是已经被阻塞了, 不会把它再调度起来.
     *  但是yield是暂时把调度权让给其他线程, 下次也可能会被调度到.
     */
    thread.join();

    //若Callable任务完成，返回True
    if(futureTask.isDone()){
      System.out.println(Thread.currentThread().getName());
      System.out.println(futureTask.get());
    }
  }
}
