package com.jinbiao.lintCode_subject.conditionalStatements;

/**
 * @author com.jinbiao
 * @date 2022/1/11
 * @apiNote
 */
public class LintCode22XX {
    public char getLastIndex(String str) {
        // write your code here
        char c = str.charAt(str.length() - 1);
        System.out.println(c);
        return c ;
    }


    /**
     * LintCode2290描述:
     * 给出两个整数 a 和 b，请计算 a 和 b 的最大公约数，通过 System.out.println 语句输出。
     */
    int GYS(int a,int b){
        int n=a%b;        //15%12=3
        if(n==0){        //如果a%b==0,最大公约数就是b
            return b;
        }else{          //否则,拿着15%12=3,再递归一次GYS(15,3)
            return GYS(a,n);   //GYS(15,3)->return 3
        }
    }


    /**
     * LintCode.2227 · 位运算左移三位（Java 版）
     */
    public void test(int n){
        System.out.println(n << 3);
    }


    public static void main(String[] args) {
        //new LintCode2201().getLastIndex("123");
        System.out.println(new LintCode22XX().GYS(15, 12));
        new LintCode22XX().test(3);
    }
}


/**
 1*1=1
 1*2=2 2*2=4
 1*3=3 2*3=6 3*3=9
 1*4=4 2*4=8 3*4=12 4*4=16
 **/
