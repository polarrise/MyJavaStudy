package com.jinbiao.javaStudy.StatisGetAndSet;

/**
 * @author wangjinbiao
 * @date 2023/2/8 11:14
 * @desc
 */
public class Jinbiao extends Person{
  static {
    System.out.println("子类静态代码块执行==");
  }
  {
    System.out.println("子类代码块执行==");
  }
  public Jinbiao(){
    System.out.println("子类构造方法执行==");
  }

  public static void main(String[] args) {
    Person jinbiao = new Jinbiao();
    jinbiao.setName("jinbiao");
  }
}
