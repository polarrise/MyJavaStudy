package com.jinbiao.javaStudy.StatisGetAndSet;

/**
 * @author wangjinbiao
 * @date 2023/2/8 11:34
 * @desc
 */
public class SonClass extends AbstractFatherClass{
  static {
    System.out.println("子类静态代码块执行==");
  }
  {
    System.out.println("子类代码块执行==");
  }
  public SonClass(){
    System.out.println("子类构造方法执行==");
  }

  public static void main(String[] args) {
    //抽象类和接口都不能直接new
    //AbstractFatherClass abstractFatherClass = new AbstractFatherClass();
    //TestInterface testInterface = new TestInterface();

    System.out.println("A".equals("B"));
  }
}
