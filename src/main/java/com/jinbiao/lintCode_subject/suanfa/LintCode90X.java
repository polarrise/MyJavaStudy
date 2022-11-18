package com.jinbiao.lintCode_subject.suanfa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 描述
 给定一个已排序的整数数组和整数a,b,c。对数组中的每个元素xx应用二次函数f(x)=ax^2+bx+c。
 返回的数组必须是有序的。
 */
public class LintCode90X {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        List<Integer> list=new ArrayList<Integer>();
        for (int i=0;i<nums.length;i++){
             list.add(a*nums[i]*nums[i]+b*nums[i]+c);
        }
        Collections.sort(list);
        int[] ints = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(list);
        return ints;
    }

    public static void main(String[] args) {
        int [] nums={-4, -2, 2, 4};
        int a = 1, b = 3, c = 5;
        new LintCode90X().sortTransformedArray(nums,a,b,c);

    }

}
