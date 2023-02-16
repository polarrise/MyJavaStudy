package com.jinbiao.javaStudy.lintCode_subject.conditionalStatements;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author com.jinbiao
 * @date 2022/1/17
 * @apiNote
 */
public class LintCode24XX {
    /**
     * 2426 · 打印素数
     * 描述:你的代码需要从标准输入流（控制台）中读入一个正整数 n，然后计算区间 [1,n] 的所有素数，计算出结果并打印到标准输出流（控制台）中，每个素数占一行。
     * 1≤n≤10000
     * 素数是除了 1 和它自身外，不能整除其他自然数的数
     */
    public void main(int n){
        for (int i = 2; i <= n; i++) { // 使用for循环遍历区间[2,n]
            boolean isPrime = true;
            int m = (int)Math.sqrt(i);    //返回 {@code double} 值的正确舍入正平方根,  根号2为1，根号3为1,根号4为2，根号5为2.....
            for (int j = 2; j <= m; j++) { // 在 2 到 sqrt(i) 中判断有无 i 的因子
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
    }
    /**
     * LintCode.2422 · 找出字符串中子串的位置索引
     * 描述:在类 Solution 中，含有名为 getIndexArray 的方法，需要你传入一个母字符串 str，一个子字符串 subStr，
     * 找出 subStr 出现在 str 中的所有索引位置，以整型数组保存，若 str 中不包含 subStr ，则返回一个仅包含 -1 的整型数组。
     */
    public int[] getIndexArray(String str, String subStr) {  //str:"asAAfgasaAAbcAAgs", subStr:"AA"
        int index = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        // 循环 找到对应的索引 则放入集合中
        while ((index = str.indexOf(subStr, index)) != -1) {  //从指定的索引0开始，返回第一次出现的指定子字符串"AA"在此字符串"asAAfgasaAAbcags"中的索引。
            list.add(index);
            index = index + subStr.length();   //index=2
        }
        // 如果size为0 则代表没有找到索引
        if (list.size() == 0) {
            return new int[] { -1 };
        }

        int[] arr = new int[list.size()];
        // 遍历 list 将元素放入到数组中
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    /**
     * Java中字符串indexof() 的使用方法
     * Java中字符串中子串的查找共有四种方法(indexof())
     * indexOf 方法返回一个整数值，指出 String 对象内子字符串的开始位置。如果没有找到子字符串，则返回-1。
     * 如果 startindex 是负数，则 startindex 被当作零。如果它比最大的字符位置索引还大，则它被当作最大的可能索引。
     * Java中字符串中子串的查找共有四种方法，如下：
     * 1、int indexOf(String str) ：返回第一次出现的指定子字符串在此字符串中的索引。
     * 2、int indexOf(String str, int startIndex)：从指定的索引处开始，返回第一次出现的指定子字符串在此字符串中的索引。
     * 3、int lastIndexOf(String str) ：返回在此字符串中最右边出现的指定子字符串的索引。
     * 4、int lastIndexOf(String str, int startIndex) ：从指定的索引处开始向后搜索，返回在此字符串中最后一次出现的指定子字符串的索引。
     */
    public void testIndexOf(){
        String s = "xXccxxxXX";
        // 从头开始查找是否存在指定的字符         //结果如下
        System.out.println(s.indexOf("c"));     //2
        // 从第四个字符位置开始往后继续查找，包含当前位置
        System.out.println(s.indexOf("c", 3));  //3
        //若指定字符串中没有该字符则系统返回-1
        System.out.println(s.indexOf("y"));     //-1
        System.out.println(s.lastIndexOf("x")); //6
    }
    public static void main(String[] args) {
        new LintCode24XX().main(7);
        int[] indexArray = new LintCode24XX().getIndexArray("asAAfgasaAAbcAAgs", "AA");
        Arrays.stream(indexArray).forEach(a->{
            System.out.println(a);
        });
        System.out.println("---------------------");
    }
}
