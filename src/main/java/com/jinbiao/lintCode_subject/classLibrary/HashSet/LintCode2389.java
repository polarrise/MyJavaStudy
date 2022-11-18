package com.jinbiao.lintCode_subject.classLibrary.HashSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author com.jinbiao
 * @date 2022/2/10
 * @apiNote
 */
public class LintCode2389 {
    /**
     * 当传入的数组为 1 2 2 3 3 4 4，将输出:
     * 1
     * 2
     * 3
     * 4
     * 样例 2：
     * 当传入的数组为 5 5 5 5 5 时，将输出:
     * 5
     */
    public Integer[] deDuplicate(Integer[] arr) {
        Set<Integer> set=new HashSet<Integer>();
        Arrays.stream(arr).forEach(a->{
            set.add(a);
        });
        Integer[] array = new Integer[set.size()];
        Integer[] arr2=set.toArray(array);  //将HashSet转换为数组。
        Arrays.sort(arr2);                 //数组排序
        Arrays.stream(arr2).forEach(a->{
            System.out.println(a);
        });
        return arr2;
    }

    public HashSet<String> createHashSet(String str1, String str2, String str3,
                                         String str4) {
        // write your code here
        HashSet<String> set=new HashSet<String>();
        set.add(str1);
        set.add(str2);
        set.add(str3);
        set.add(str4);
        return  set;
    }

    public static void main(String[] args) {
        Integer []arr={10001 ,1000 ,1000 ,100 ,10 ,1};
        new LintCode2389().deDuplicate(arr);
    }
}
