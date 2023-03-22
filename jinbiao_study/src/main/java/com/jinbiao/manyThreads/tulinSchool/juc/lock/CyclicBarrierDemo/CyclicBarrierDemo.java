package com.jinbiao.manyThreads.tulinSchool.juc.lock.CyclicBarrierDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author：Jinbiao
 * @Date：2023/3/12 16:44
 * @Desc：模拟人满发车
 * 利用CyclicBarrier的计数器能够重置，屏障可以重复使用的特性，可以支持类似“人满发车”的场景
 *
 * 结合现实意义:就像是出去旅游，大家不同时间到了景区门口，但是景区疫情限流，先把栅栏拉下来，在景区里的游客走一批，打开栅栏，再放进去一批，走一批，再放进去一批……
 * 这就是CyclicBarrier的两个特性: 栅栏：多个线程相互等待，到齐后再执行特定动作。 循环：所有线程释放后，还能继续复用它
 * 当然，CyclicBarrier的实现其实还是基于lock+condition，多个线程在到达一定条件前await，到达条件后signalAll。
 *
 * 应用场景总结:
 * 以下是一些常见的 CyclicBarrier 应用场景：
 * 多线程任务：CyclicBarrier 可以用于将复杂的任务分配给多个线程执行，并在所有线程完成工作后触发后续操作。
 * 数据处理：CyclicBarrier 可以用于协调多个线程间的数据处理，在所有线程处理完数据后触发后续操作。
 *
 * AQS 组件总结。
 * Semaphore(信号量)-允许多个线程同时访问： synchronized 和 ReentrantLock 都是一次只允许一个线程访问某个资源，Semaphore(信号量)可以指定多个线 程同时访问某个资源。
 *
 * CountDownLatch （倒计时器）： CountDownLatch 是一个同步工具类，用来协调多个线程之间的同步。这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结束，再开始执行。
 *
 * CyclicBarrier(循环栅栏)： CyclicBarrier 和 CountDownLatch 非常类似，它也可以实现线程间的技术等待，但是它的功能比 CountDownLatch 更加复杂和强大。
 * 主要应用场景和 CountDownLatch类似。CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，
 * 所有被屏障拦截的线程才会继续干活。CyclicBarrier默认的构造方法是 CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，每个线程调用 await 方法告诉 CyclicBarrier 我已经到达了屏障，然后当前线程被阻塞。
 */
public class CyclicBarrierDemo {

    private static final ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    private static final List<Integer> idLists = new ArrayList<Integer>(10);
    /**
     * 验证id
     * @return
     */
    public static synchronized Boolean veriyId(){
        Integer id = threadLocal.get();
        System.out.println("当前线程的身份id:"+id+"检票");
        Collections.addAll(idLists, 1,  3,4,5,6,7,8,9,10);
        if(idLists.contains(id)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        /**
         * CyclicBarrier初始化参与者为5，那么一定要等到5个人满才发发车，该例子里面如果2号校验身份失败,那么不会发车，CyclicBarrier会等待第6个人到了凑满5个人才会发车。 然后后面的7-8-9-10上车了，因为车没满少了一个人,所以不会发车。。
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,
                () -> System.out.println("人齐了，准备发车==="));

        for (int i = 0; i < 10; i++) {
            final int id = i+1;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(id+"号马上就到");
                        threadLocal.set(id);

                        if(!veriyId()){
                            System.out.println("id="+id+",身份校验失败");
                            return;
                        }
                        //校验身份id:
                        System.out.println("id="+id+",身份校验成功!");
                        Thread.sleep(ThreadLocalRandom.current().nextInt(2000));

                        System.out.println(id + "号到了，上车");

                        /**
                         * 返回当前线程的到达索引，其中索引｛@code getParties（）-1｝表示第一个到达，零表示最后一个到达。
                         * 如果当前线程不是最后一个到达的线程，那么出于线程调度的目的，它将被禁用，并处于休眠状态，直到发生以下情况之一：
                         * 1.最后一个线程到达
                         * 2.或其他线程｛@linkplain Threadinterrupt｝中断当前线程；
                         * 3.或其他线程｛@linkplain Threadinterrupt interrupt｝其他等待线程之一；
                         * 4.或其他线程在等待屏障时超时；
                         * 5.或其他线程调用此屏障上的｛@link reset｝如果当前线程：在进入该方法时设置了中断状态；
                         * 6.或在等待时被｛@linkplain Threadinterrupt interrupt｝，则抛出｛@link InterruptedException｝并清除当前线程的中断状态<p> 如果在任何线程等待时屏障是｛@link reset｝，或者如果在｛@code await｝
                         */
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch(BrokenBarrierException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
