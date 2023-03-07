package com.jinbiao.javaStudy.lintCode_subject.conditionalStatements;

/**
 * @author com.jinbiao
 * @date 2022/1/17
 * @apiNote
 */
public class LintCode23XX {

    /**
     * 2341 · 求小写字母个数
     * 描述:请从标准输入流（控制台）中获取一个由大写字母和小写字母组成的字符串 s，并通过 System.out.println 语句输出 s 中小写字母的个数。
     */
    public int test(String str){
        char[] c=str.toCharArray();
        int lowercaseLetters=0;
        for(int i=0;i<c.length;i++){
            if(c[i]>='a'&&c[i]<='z'){
                lowercaseLetters++;
            }
        }
        return lowercaseLetters;
    }

    /**
     * LintCode2359:打印99乘法表
     */
    public void print99(){
        for(int i=1;i<=9;i++){
            for(int j=1;j<=9;j++){
                if(i>=j){    //i:1,j:1   i:2,j:1
                    if(i==j){
                        System.out.println(j+"*"+i+"="+i*j+" ");
                    }else{
                        System.out.print(j+"*"+i+"="+i*j+" ");
                    }
                }
            }
        }
    }

    /**
     * LintCode.2375 · 判断 2 的幂
     * 描述:给定一个正整数 n，判断它是否是 2 的幂次方。
     * 如果是，则返回 It's a power of two；否则，则返回 It's not a power of two。
     * 整数 n 是 2 的幂次方需要满足：存在整数 x 使得 n == 2^xn==2
     * x
     */
    public void twoOfciFang(int n){
        int answer=1;
        boolean flag=false;
        for(int i=0;i<=n;i++){
            answer*=2;
            if(answer==n){
                flag=true;
                break;
            }
        }
        if(flag||n==1){
            System.out.println("It's a power of two");
        }else{
            System.out.println("It's not a power of two");
        }
    }
    /**
     * LintCode.2376 · 判断回文数
     描述
     请编写代码，判断传入的数字是否为回文数。
     设 n 是一任意自然数。若将 n 的各位数字反向排列所得自然数 n1 与 n 相等，则称 n 为一回文数。
     在本题的 Solution 类中有个 judgePalindrome 方法，该方法有一个 int 类型的参数 number，number 代表传入的数字。该方法要判断传入的数字是否为回文数，然后返回这个判断结果。返回值为 boolean 类型。
     */
    public boolean judgePalindrome(int number) {
        String str=String.valueOf(number);
        StringBuffer strBuffer=new StringBuffer(str);
        StringBuffer reverse = strBuffer.reverse();
        String bufferToString=reverse.toString();
        if(str.equals(bufferToString)){
            return true;
        }else{
            return false;
        }
    }
    /**
     * LintCode.2386 · 求 a 的 b 次方
     * 描述:请从标准输入流（控制台）中获取两个正整数 a，b，要求计算出 a 的 b 次方，并用 System.out.println 语句输出计算结果到标准输出流（控制台）。
     */
    public int ciFang(int a,int b){//a:2,b:3
        int answer = 1;
        for(int i=1;i<=b;i++){
            answer*=a;
        }
        System.out.println(answer);
        return answer;
    }


    /**
     * LintCode.2398 · 逆序输出整数
     * 描述:请从标准输入流（控制台）中获取一个两位正整数 n，通过 System.out.println 语句输出该正整数的逆序到标准输出流（控制台）。
     */
    public void reverseInt(int n){
        int a = n % 10;
        int b = n / 10;
        int c = a * 10 + b;

        System.out.println(c);
    }
    public static void main(String[] args) {
        System.out.println(new LintCode23XX().test("Apple"));
        System.out.println("--------------");
        new LintCode23XX().print99();
        new LintCode23XX().twoOfciFang(1);
        new LintCode23XX().twoOfciFang(8);
        new LintCode23XX().twoOfciFang(11);
        System.out.println("--------------");
        System.out.println(new LintCode23XX().judgePalindrome(123321));
        System.out.println(new LintCode23XX().judgePalindrome(123322));
        System.out.println("--------------");
        new LintCode23XX().ciFang(2,3);
        System.out.println("--------------");
        new LintCode23XX().reverseInt(75);
        new LintCode23XX().reverseInt(50);
    }
}
