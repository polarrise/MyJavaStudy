package com.jinbiao.manyThreads.TulinSchool.volatileDemo;

/**
 * @author wangjinbiao
 * @date 2023/1/12$ 11:37$
 * @desc
 */
public class VolatileDemo1 {
  public static volatile boolean flag = true;

  public static void main(String[] args) throws InterruptedException {


      new Thread(()->{
          while (flag){

          }
        System.out.println("flag改为了true");
      }).start();

    Thread.sleep(100);

    flag = false;
    System.out.println("flage changed : false");

  }
}
