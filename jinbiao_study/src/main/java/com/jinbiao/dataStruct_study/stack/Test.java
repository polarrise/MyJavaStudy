package com.jinbiao.dataStruct_study.stack;

import java.util.Scanner;

/**
 * @author com.jinbiao
 * @date 2022/2/14
 * @apiNote
 */
public class Test {
    /**
     *push(val) 将 val 压入栈
     */
    public void push(String val) {
       //定义先关节点， 存入这个字符串val,进入压入栈
    }

    /**
     *pop() 将栈顶元素弹出, 并返回这个弹出的元素
     */
    public String pop() {

        return null ;// 返回一个"}"
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str = sc.next();//"{{{}}}{{}}{"
        // str 分割
        Test test=new Test();

        String[] split = str.split("");

        for(int i=0;i<split.length;i++){
            if(split[i].equals("}")){
                test.pop();
            }
        }
        //if(test不为空)  代表栈没有出完，  即不完成匹配~


    }
}
