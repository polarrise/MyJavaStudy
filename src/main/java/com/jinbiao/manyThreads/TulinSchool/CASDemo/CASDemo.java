package com.jinbiao.manyThreads.TulinSchool.CASDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CASDemo {
    //AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题

    // 正常在业务操作，这里面比较的都是一个个对象。        //构造初始引用为1,初始标记(版本号)为1的atomicStampedReference对象
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

    public static void main(String[] args) {   // CAS  compareAndSet : 比较并交换！
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("线程1的初始版本号version:"+stamp);
            System.out.println("线程1的初始引用:"+atomicStampedReference.getReference());
            try {
                TimeUnit.SECONDS.sleep(1);    //休眠1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Lock lock = new ReentrantLock(true);

//atomicStampedReference的当前引用是否等于预期引用，并且atomicStampedReference的当前标志是否等于预期标志，如果全部相等，则以原子方式将该引用和该标志的值设置为给定的更新值。
            System.out.println("当前的引用、版本号是否都与预期的一致?"+
atomicStampedReference.compareAndSet(atomicStampedReference.getReference(), 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            //上一步已经把atomicStampedReference里面的Pair对象的当前引用和版本号更新为了2,2
            System.out.println("进行了一次CAS之后的引用:"+atomicStampedReference.getReference());
            System.out.println("进行了一次CAS之后的版本号:"+atomicStampedReference.getStamp());

            System.out.println("是否可以发生了第二次CAS?"+atomicStampedReference.compareAndSet(atomicStampedReference.getReference(), 3, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("进行了两次CAS之后,线程1的引用:"+atomicStampedReference.getReference());
            System.out.println("进行了两次CAS之后,线程1版本号version:"+atomicStampedReference.getStamp());

        },"线程1").start();

        // 乐观锁的原理相同！
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("线程2的初始版本号version:"+stamp);
            System.out.println("线程2的初始引用:"+atomicStampedReference.getReference());
            try {
                TimeUnit.SECONDS.sleep(2);        //休眠2秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //因为发生了两次CAS之后 预期引用变成了3,预期版本后也变成了3.
            System.out.println("线程2继续做第三次CAS:"+atomicStampedReference.compareAndSet(atomicStampedReference.getReference(), 6, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("线程2获得到的引用:"+atomicStampedReference.getReference());
            System.out.println("线程2获得到的版本号version:"+atomicStampedReference.getStamp());
        },"线程2").start();
    }
}
