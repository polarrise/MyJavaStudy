package com.jinbiao.Optional;

public class Test {
    public static void main(String[] args) {
        int a=1;
        Integer b=null;

        if(b!=null && b.intValue()!=1){
            System.out.println("b不等于1");
        }else{
            System.out.println("b等于1");
        }

    }
}
