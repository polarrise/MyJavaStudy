package com.jinbiao.manyThreads.tulinSchool.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangjinbiao
 * @date 2023/1/31 14:09
 * @desc
 */
public class JOLDemo1 {
  public static void main(String[] args) throws InterruptedException {
    Object o = new Object();
    System.out.println(ClassLayout.parseInstance(o).toPrintable());

    //-XX:UseBiasedLocking  :是否打开偏向锁,默认是不打开的
    //-XX:BiasedLockingStartupDelay   默认是4s
    Thread.sleep(5000);

    Object o2 = new Object();
    System.out.println(ClassLayout.parseInstance(o2).toPrintable());




    for (int i=0;i<3;i++){
      int finalI = i;
      new Thread(()->{
        synchronized (o2){
          System.out.println("循环获取锁："+ finalI +"次");
          System.out.println(ClassLayout.parseInstance(o2).toPrintable());
        }
      }).start();
    }
  }
}
