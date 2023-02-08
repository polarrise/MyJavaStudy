package com.jinbiao.javaStudy.StatisGetAndSet;

/**
 * @author wangjinbiao
 * @date 2023/2/8 11:33
 * @desc
 */
public abstract class AbstractFatherClass {
  static {
    System.out.println("抽象类父类静态代码块执行==");
  }
  {
    System.out.println("抽象类父类代码块执行==");
  }
  public AbstractFatherClass(){
    System.out.println("抽象类父类构造方法执行==");
  }
}
