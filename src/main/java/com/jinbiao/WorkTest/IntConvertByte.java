package com.jinbiao.WorkTest;

/**
 * @author wangjinbiao
 * @date 2022/11/24$ 9:58$
 * @desc
 */
public class IntConvertByte {
  /**
   * byte类型的范围是 -128 ~ 127
   * @param args
   */
  public static void main(String[] args) {
    Integer a=127;
    Integer b=128;
    Integer c=129;
    Integer d=130;
    System.out.println(a.byteValue());
    System.out.println(b.byteValue());
    System.out.println(c.byteValue());
    System.out.println(d.byteValue());
  }
}
