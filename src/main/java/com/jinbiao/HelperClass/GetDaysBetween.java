package com.jinbiao.HelperClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GetDaysBetween {
  public static void main(String[] args) throws ParseException {
    Random random = new Random();

    final int i = random.nextInt(2)+1;
    System.out.println(i);


    String a = "2022-08-07"; // 时间字符串
    String b = "2022-08-15"; // 时间字符串
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    final Date date = sdf.parse(a);

    final int daysBetween = getDaysBetween(a, b);
    System.out.println(daysBetween);

  }

  /**
   * @param smallDate
   * @param bigDate
   * @desc 获取两个日期之间的天数
   */
  public static int getDaysBetween(String smallDate, String bigDate) throws ParseException {
    // 日期格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // 获取两个日期的时间戳
    Calendar cal = Calendar.getInstance();
    cal.setTime(sdf.parse(smallDate));
    long smallTime = cal.getTimeInMillis();
    cal.setTime(sdf.parse(bigDate));
    long bigTime = cal.getTimeInMillis();
    // 相差的日期
    long days = (bigTime - smallTime) / (1000 * 3600 * 24);
    // long转int 存在溢出情况  根据业务情况编辑catch中返回值
    try {
      return Integer.parseInt(String.valueOf(days));
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return 0;
    }
  }
}
