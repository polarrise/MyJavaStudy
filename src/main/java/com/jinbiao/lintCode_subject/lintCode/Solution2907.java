package com.jinbiao.lintCode_subject.lintCode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author com.jinbiao
 * @date 2021/10/29
 * @apiNote
 */
public class Solution2907 {

    static String[] day = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    static Map<String,String> map=new ConcurrentHashMap<String,String>();
    /**
     * 手动计算日期
     *@param space 间隔天数
     */
    public static String getDate(String data, int space) {
        String[] datas = data.split("-");
        int y = Integer.parseInt(datas[0]);
        int m = Integer.parseInt(datas[1]);
        int d = Integer.parseInt(datas[2]);
        return getDate(y, m, d, space);
    }

    public static String getDate(int year, int moon, int day, int space) {

        int nd = day + space;
        boolean leapYear = year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
        int max = 31;
        switch (moon) {
            case 2:
                max = 28 + (leapYear ? 1 : 0);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                max = 30;
                break;
        }
        if (nd > max) {
            moon++;
            if (moon > 12) {
                year++;
                moon = 1;
            }
            day = 1;
            space = nd - max - 1;
            return getDate(year, moon, day, space);
        }
        return year + "-" + moon + "-" + nd;
    }

    static Thread[] getWeekDay() throws Exception {
        Thread[] weekDay = new Thread[7];
        Thread t1= new Thread(){
            public void run(){

            }
        };

        return weekDay;
    }
}
