package com.jinbiao.javaStudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test2 {
  public static void main(String[] args) {
    //String str=String.format("Hello %s，我是 %s，今年 %s 岁", "CSDN","小猪","12");
    //System.out.println(str);
    //
    //String str2="aaa";
    //
    //str2+="\r\n"+"bbb";
    //System.out.println(str2);
    //
    ////这样在str后面就有换行了．
    //
    //
    //int s=15550817;
    //
    //System.out.println(s/(24*3600));



    Random random = new Random();
    final int randomAddAnswersNum = random.nextInt(6);

    System.out.println(randomAddAnswersNum);


    List<Integer> list= new ArrayList<>();
    list.add(null);
    list.add(1);
    list.add(2);
    System.out.println(list);

    Integer a=null;
    //if(a.equals("1")){
    //  System.out.println("111");
    //}

    if("1".equals(a)){
      System.out.println("111");
    }
  }
}
