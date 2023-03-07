package com.jinbiao.lambdas.Stream.API;

import java.util.ArrayList;
import java.util.List;

public class MyStream1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("陈奕迅");
        list.add("陈小春");
        list.add("钟汉良");
        list.add("陈七");
        list.add("陈伟霆");
        //筛选陈开头，名字是三个字的
        List<String> chenList = new ArrayList<>();
        for(String item : list){
            if(item.startsWith("陈")){
                chenList.add(item);
            }
        }

        List<String> threeList = new ArrayList<>();
        for(String item : chenList){
            if(item.length()==3){
                threeList.add(item);
            }
        }

        //遍历输出符合条件的
        for(String item : threeList){
            System.out.println(item);
        }

        System.out.println("=====================");

        //使用Stream流
        list.stream().filter(str->str.startsWith("陈"))
                .filter(str->str.length()==3)
                .forEach(str-> System.out.println(str));
    }
}
