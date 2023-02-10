package com.jinbiao.javaStudy.compare;

import io.swagger.models.auth.In;

/**
 * @author wangjinbiao
 * @date 2023/2/10 9:33
 * @desc
 */
public class IntegerCompare {
  public static void main(String[] args) {

    //两个new生成的Integer变量的对比:永远是不相等的（因为new生成的是两个对象，其内存地址不同）。
   // Integer a = new Integer(10000);
   // Integer b = new Integer(10000);
   // System.out.println(a == b);           //false
   //
   // //Integer变量和int变量的对比:只要两个变量的值是向等的，则结果为true,Integer自动拆箱，实际比较的是两个int值
   // int c = 10000;
   // Integer d =new Integer(10000);
   // Integer e = 10000;
   // System.out.println(c == d);     //true
   // System.out.println(c == e);    //true
   //
   ///* 非new生成的Integer变量和new Integer()生成变量的对比:结果为false。（因为非new生成的
   // Integer变量指向的是java常量池中的对象，而new Integer()生成的变量指向堆中新建的对象，两者在内
   // 存中的地址不同）*/
   // Integer f = new Integer(10000);
   // Integer g = 10000;
   // System.out.println(f == g);   //false

    //两个非new生成的Integer对象的对比:如果两个变量的值在区间-128到127之间，则比较结果为true，如果两个变量的值不在此区间，则比较结果为false
    Integer h = 100;
    Integer i = 100;
    System.out.println(h == i);   //true
    Integer j = 128;
    Integer k = 128;
    System.out.println(j == k);   //false
  }
}
