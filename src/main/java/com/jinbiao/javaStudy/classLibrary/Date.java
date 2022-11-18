package com.jinbiao.javaStudy.classLibrary;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author com.jinbiao
 * @date 2022/1/7
 * @apiNote
 */
public class Date {
    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static java.util.Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public String dateConversion(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date=simpleDateFormat .parse(str);  //字符串转换成日期
        SimpleDateFormat simpleDateFormat2  =new SimpleDateFormat("yyyy年m月d日");
        String dateString=simpleDateFormat2.format(date).toString();
        return dateString;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(new Date().dateConversion("2021-11-11"));
    }
}
