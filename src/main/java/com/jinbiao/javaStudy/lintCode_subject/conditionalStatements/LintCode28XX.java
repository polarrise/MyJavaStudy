package com.jinbiao.javaStudy.lintCode_subject.conditionalStatements;

import java.util.Arrays;

/**
 * @author com.jinbiao
 * @date 2022/1/17
 * @apiNote
 */
public class LintCode28XX {

    /**
     LintCode.2815 · 你的存款可以用多久
     描述:假设你有一笔存款，你每天的消费金额是你当前存款的一半，请计算你的存款在多少天之后会被全部用尽。
     */
    public void saveTime(int n){
        int count=0;
        while((n-n/2)>0){
            count++;
            n=n/2;
        }
        System.out.println("Can be spent in "+count+" days");
    }


    /**
     * 2819 · 判断质数
     * 描述:输入一个任意正整数 n，判断它是否为质数，如果是质数就输出 ‘是质数‘，否则输出 ’不是质数‘。
     * 质数：除了1和它本身之外,不能被其他数整除的正整数称为质数。
     */
    public void ifZhiShu(int n){   //拿这n从2到n开始取模,如果有取模后为0的代表这个n不是质数,break退出循环
        boolean siZhiShu=true;
        for(int i=2;i<n;i++){
            if(n%i==0){
                siZhiShu=false;
                break;
            }
        }
        if(siZhiShu){
            System. out . println(n+" "+"is a prime number");
        }else{
            System. out . println(n+" "+"is not a prime number" );
        }
    }

    /**
     * LintCode.2820 · 小数字的和
     *描述:先输入一个数字 n，再输入一个 n 个数的数组，求出这个数组中不与该数组中最大数相同的数字之和。
     */
    public int minArrmaxnumSum(int n,int[] arr){
        int max= Arrays.stream(arr).max().getAsInt();  //我们使用流的操作，来实现找到数组的最大值，底层原理也是数组元素逐个比较。
        int sum= Arrays.stream(arr).filter(a -> a != max).sum();  //对数组中不等于最大值的元素求和
        return sum;
    }

    /**
     * 2826 · 数字 2 的朋友
     * 描述:我们定义判断一个 int 类型的数字是不是数字 2 的朋友的标准是，这个数字是不是由不同的两个 2 的幂次方数相加得到的。现在传入一个数字 n 请你判断它是否是数字 2 的朋友，如果是则返回 true，否则返回 false。
     */
    public static boolean isFriend(int n) {
        //注意到符合条件的数满足特点“其二进制形式有且仅有2个位为1，其余位均为0”，借助Integer.bitCount函数可以直接得到其二进制中为1的位数，解答如下：
        System.out.println(Integer.bitCount(n)==2);
        return Integer.bitCount(n)==2;
    }



    public static void main(String[] args) {
        new LintCode28XX().saveTime(1000);
        new LintCode28XX().ifZhiShu(4);
        new LintCode28XX().isFriend(10);
        System.out.println("--------------");
        int [] inArr={5 ,8 ,7 ,3 ,4 ,4 ,4 ,6 ,8 ,8};
        System.out.println(new LintCode28XX().minArrmaxnumSum(10,inArr));
    }
}
