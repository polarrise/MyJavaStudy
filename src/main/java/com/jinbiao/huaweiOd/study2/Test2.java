package com.jinbiao.huaweiOd.study2;

/**
 * substring(beginIndex,endIndex)  包含首不含尾
 * substring(beginIndex)   从该号索引开始往后截,包含该索引
 */
public class Test2 {

    public static void test(String str){  //length:13
           while(str.length()>8){
               System.out.println(str.substring(0,8));
               str=str.substring(8);
           }
           if(str.length()<8 &&str.length()>0){
               str+="00000000";
               System.out.println(str.substring(0,8));
           }
    }
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        String s = sc.nextLine();

        test("abcdefghijklm");

        //abcdefgh   s.substring(0,8)
        //ijklm      s.substring(8)
    }
}
