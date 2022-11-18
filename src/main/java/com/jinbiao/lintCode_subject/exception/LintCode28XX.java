package com.jinbiao.lintCode_subject.exception;

import java.util.Arrays;

/**
 * @author com.jinbiao
 * @date 2022/1/17
 * @apiNote
 */
public class LintCode28XX {
    /**
     * LintCode.2849 · 倒序数组
     * 描述:现在有一个方法 invertedArray(int n , int k) 用来创建倒序数组，倒数数组的规则如下：
     * 1.数组长度为 n。
     * 2.数组从 0 ~ n-1 中依次填充 k ~ 0。
     * 3.在填充时，数组中的值为非负数，若 k 小于 n ，不足填充 0，若 k 大于 n 需要抛出异常信息 The length of parameter k is not legal.。
     */
    public int[] invertedArray(int n , int k) throws Exception { //n:5, k:5
        int[]arr=new int [n];
        if(k>n){
            System.out.println("The length of parameter k is not legal.");
        }
        else{
            for(int i=0;i<=n-1;i++){
                if(k>0){
                    arr[i]=k;
                    k=k-1;
                }else
                    arr[i]=0;
            }
        }
        return arr;
    }

    public void test(){
        int []arr={};
        arr[0]=1;   //注意不能把arr数组声明为空对象,然后给arr数组元素赋值,报错java.lang.ArrayIndexOutOfBoundsException。应该先声明数组指定长度,然后在添加数组索引上的元素
        Arrays.stream(arr).forEach(a->{
            System.out.println(a);
        });
    }

    public static void main(String[] args) throws Exception {
        try {
            LintCode28XX lintCode28XX = new LintCode28XX();
            int n=5,k=4;
            int[] rs = lintCode28XX.invertedArray(n,k);
            //int n=10,k=20;
            //int[] rs = lintCode28XX.invertedArray(n,k);

            if (k <= n) {
                StringBuilder stringBuilder = new StringBuilder("[");
                for (int num : rs) {
                    stringBuilder.append(num + ", ");
                }
                stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
                stringBuilder.append("]");
                System.out.println(stringBuilder.toString());
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            if ("The length of parameter k is not legal.".equals(msg)) {
                System.out.println(msg);
            } else {
                e.printStackTrace();
            }
        }

        //new LintCode28XX().test();
    }
}
