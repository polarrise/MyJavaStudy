package com.jinbiao.javaStudy.lintCode_subject.classLibrary.ArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 样例一:
 如果 list 中的数据为 1 3 2 则输出：
 3
 2
 1
 样例二:
 如果 list 中的数据为 4 5 6 则输出：
 6
 5
 4
 */
public class LintCode2419 {
    public void sortPrint(ArrayList<Integer> list) {
        // write your code here
        list = (ArrayList<Integer>) list.stream().sorted((a, b)->b-a).collect(toList());
        list.stream().forEach(a->{
            System.out.println(a);
        });
    }

    public void sortPrint2(ArrayList<Integer> list) {
        // Comparator 的静态方法 reverseOrder 方法返回一个与自然排序相反的的比较器
        // 即对集合元素进行逆序排序
        list.sort(Comparator.reverseOrder());   //反序
        list.sort(Comparator.naturalOrder());  //正序
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    public List<String> sortName(List<String> list) {
        list.sort(Comparator.naturalOrder());  //正序
        for (String i : list) {
            System.out.println(i);
        }
        return list;
    }

    public List<String> sortName2(List<String> list) {  //忽略大小写正序排序
        list.sort(String.CASE_INSENSITIVE_ORDER);
        for (String i : list) {
            System.out.println(i);
        }
        return list;
    }
    public List<String> sortName22(List<String> list) {
        Collections.sort(list, new Comparator<String> () {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);//此方法按照字典顺序进行排序，不考虑大小写，返回一个整数
            }
        });
        for (String i : list) {
            System.out.println(i);
        }
        return list;
    }
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(3);
        list.add(2);

        LintCode2419 solution = new LintCode2419();
        solution.sortPrint(list);
        solution.sortPrint2(list);

        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("Benson");
        list2.add("Clark");
        list2.add("Adrian");
        solution.sortName(list2);
        System.out.println("----------");

        list2.add("bill");
        solution.sortName2(list2);
        System.out.println("----------");
        solution.sortName22(list2);
    }
}
