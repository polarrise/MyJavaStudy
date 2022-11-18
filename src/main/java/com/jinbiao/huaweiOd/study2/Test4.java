package com.jinbiao.huaweiOd.study2;

import java.util.Scanner;

public class Test4 {
    /**
     * 输入描述：
     * 输入一行，每行空格分割，分别是年，月，日
     * 输出描述：
     * 输出是这一年的第几天
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dayofmonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        while(sc.hasNext()){
            int year = sc.nextInt();
            int month = sc.nextInt();
            int day = sc.nextInt();
            int count = 0; //统计天数
            for(int i = 0; i < month - 1; i++) { //取前面的月份
                count += dayofmonth[i];
            }
            count += day; //当月天数
            if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) { //闰年
                if (month > 2) //大于2月的多加一天
                    count++;
            }
            System.out.println(count);
        }
    }
}
