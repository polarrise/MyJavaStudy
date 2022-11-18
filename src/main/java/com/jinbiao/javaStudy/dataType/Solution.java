package com.jinbiao.javaStudy.dataType;

import java.util.Scanner;

/**
 * @author com.jinbiao
 * @date 2022/1/7
 * @apiNote
 */
public class Solution {


    public void test1(){
        Scanner scanne=new Scanner(System.in);
        int num=scanne.nextInt();
        if(num<128 && num >=0){
            System.out.println(num>0?(char)num:"error");
        }else {
            System.out.println("error");
        }
    }

    public void test2(){
        // 通过 `Scanner` 类获取控制台输入流
        Scanner scanner = new Scanner(System.in);
        // 获取控制台输入的一个字符串
        String s = scanner .next();
        // 得到第一个字符
        char c = s.charAt(0);
        //打印第一个字符的ASCII码
        System.out.println(Integer.valueOf(c));
    }
    public static void main(String[] args) {
            new Solution().test1();
    }
}
