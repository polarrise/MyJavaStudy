package com.jinbiao.WorkTest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * compareTo只比较值，其中返回结果：-1表示小于，0表示等于，1表示大于
 */
public class BigDecimalTest {
    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.DATE, n);//增加一天
            //cd.add(Calendar.MONTH, n);//增加一个月

            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args) {
        BigDecimal bigDecimal6 = new BigDecimal("0.01");
        BigDecimal bigDecimal7 = new BigDecimal("0.00");
        System.out.println(bigDecimal6.compareTo(bigDecimal7));
        System.out.println("0".equals(0));


        Date date=new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间
        cal.add(Calendar.DATE, 1);//增加一天
        Date orderEndTime = cal.getTime();
        System.out.println(date);
        System.out.println(orderEndTime);

        final int i = date.compareTo(orderEndTime);//当前时间 与订单创建时间+1天比较      相等返回0，大于返回1，小于返回-1.
        System.out.println(i);
    }
}
