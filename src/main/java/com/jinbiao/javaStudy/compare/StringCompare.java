package com.jinbiao.javaStudy.compare;

/**
 * @author wangjinbiao
 * @date 2023/2/9 11:52
 * @desc
 */
public class StringCompare {
  private static void howNumObjCreate(){
    String s1 = new String("he")+new String("llo");
    String s2 = s1.intern();
    System.out.println(s1 == s2);
  }

  private static void StrConCompare(){
    String s0 = "jinbiao";
    String s1 = "jinbiao";
    String s2 = "jin"+"biao";
    System.out.println(s0 == s1);
    System.out.println(s0 == s2);
  }

  private static void StrConCompareStrObj(){
    String s0 = "zhuge";
    String s1 = new String("zhuge");
    String s2 = "zhu" + new String("ge");
    System.out.println(s0 == s1);   //false
    System.out.println(s0 == s2);  //false
    System.out.println(s1 == s2); //false
  }

  private static void StrIsImmutable(){
    String s = "a"+"b"+"c";  //就等价于String s = "abc";
    String ss = "abc";
    String a = "a";
    String b = "b";
    String c = "c";
    String s1 = a+b+c;
    System.out.println(s == "abc");  //true
    System.out.println(s == ss);     //true
    System.out.println(s == s1);    //false
  }

  public static void main(String[] args) {
    howNumObjCreate();
    StrConCompare();
    StrConCompareStrObj();
    StrIsImmutable();
  }
}
