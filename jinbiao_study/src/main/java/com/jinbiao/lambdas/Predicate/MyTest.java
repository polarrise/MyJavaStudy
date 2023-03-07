package com.jinbiao.lambdas.Predicate;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jinbiao
 * @date 2022/4/7
 * @apiNote
 */
public class MyTest {
    public static ArrayList<String> filter(String[] arr, Predicate<String> pre1, Predicate<String> pre2) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : arr) {
            boolean b = pre1.and(pre2).test(s);
            if (b) {
                list.add(s);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String[] array = {"迪丽热巴,女", "古力娜扎,女", "佟丽娅,女", "赵丽颖,女"};
        ArrayList<String> list = filter(array,
                s -> s.split(",")[1].equals("女"),
                s -> s.split(",")[0].length() == 4);
        for(String s : list){
            System.out.println(s);
        }

        List <String>result=new ArrayList<String>();
        for(String s : array){
            boolean ifTrue = new Predicate<String>() {
                @Override
                public boolean test(String s) {
                    return s.split(",")[0].length() == 4;
                }
            }.and(new Predicate<String>() {
                @Override
                public boolean test(String s) {
                    return s.split(",")[1].equals("女");
                }
            }).test(s);
            if(ifTrue){
                result.add(s);
            }
        }
        System.out.println("在方法体里完成测试:"+result);

        final Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if(StringUtils.isNotEmpty(s)){
                    System.out.println(s);
                    return true;
                }
                return false;
            }
        };

        List<String> testList = new ArrayList<>();
        testList.add("迪丽热巴");
        testList.add("古力娜扎");
        testList.add("佟丽娅");
        testList.add("赵丽颖");
        testList.add("胡歌");
        List<String> filterList = testList.stream().filter(predicate).collect(Collectors.toList());

        List<String> filterList2 = testList.stream().filter(
            a-> {
                for (String s: array) {
                   if(a.equals(s.split(",")[0])){
                       return true;
                   }
                }
                return false;
            }).collect(Collectors.toList());

        System.out.println("filterList:"+filterList);
        System.out.println("filterList2:"+filterList2);
    }
}
