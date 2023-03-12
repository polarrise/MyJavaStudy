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
