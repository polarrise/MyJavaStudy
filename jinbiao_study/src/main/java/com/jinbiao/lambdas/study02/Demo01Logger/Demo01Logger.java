package com.jinbiao.lambdas.study02.Demo01Logger;

/**
 * @author Jinbiao
 * @date 2022/4/6
 * @apiNote
 */
public class Demo01Logger {
    //定义一个根据日志的级别,显示日志信息的方法
    public static void showLog(int level, String message){
        //对日志的等级进行判断,如果是1级别,那么输出日志信息
        if(level==1){
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        //定义三个日志信息
        String msg1 = "Hello";
        String msg2 = "World";
        String msg3 = "Java";

        //调用showLog方法,传递日志级别和日志信息
        showLog(2,msg1+msg2+msg3);

    }

}
