package com.jinbiao.lintCode_subject.lintCode;

/**
 * @author com.jinbiao
 * @date 2021/10/29
 * @apiNote
 */
public class Solution {

    public int[] replacement(int[] arr1, int[] arr2) {
        if(arr1.length!=arr2.length) {
            int x=arr1.length- arr2.length;      //定义一个变量存储从哪个位置开始替换值,
            for (int i = arr1.length- arr2.length; i < arr1.length; i++) {   //i=3,i<6;i++   //只遍历三次，从4开始遍历 i:3,4,5.        i=6;i<9:i++  i:6,7,8
                    arr1[i] = arr2[i-x];                         //arr1[3]=arr2[0],   arr1[4]=arr2[1],   arr1[5]=arr2[2],           arr1[6]=arr2[3],

            }
            return arr1;
        }else{
            arr1=arr2;
            return arr1;

        }
    }

    public static void main(String[] args) {
        int [] arr1={1, 2, 3, 4, 5, 6,7,8,9,10,11,12};
        int [] arr2={7, 8, 9};
        int[] replacement = new Solution().replacement(arr1, arr2);
        for (int i:replacement) {
            System.out.println(i);
        }
    }
}
