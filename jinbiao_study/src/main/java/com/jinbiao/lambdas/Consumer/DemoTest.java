package com.jinbiao.lambdas.Consumer;

import java.util.function.Consumer;

/**
 按照格式“姓名：XX。性别：XX。”的格式将信息打印
 要求将打印姓名的动作作为第一个Consumer接口的Lambda实例，
 将打印性别的动作作为第二个Consumer接口的Lambda实例，
 将两个Consumer接口按照顺序“拼接”到一起。
 */
public class DemoTest {
    //定义一个方法,参数传递String类型的数组和两个Consumer接口,泛型使用String
    public static void printInfo(String[] arr, Consumer<String> con1, Consumer<String> con2){
        //遍历字符串数组
        for (String message : arr) {
            //使用andThen方法连接两个Consumer接口,消费字符串
            con1.andThen(con2).accept(message);
        }
    }

    public static void main(String[] args) {
        //定义一个字符串类型的数组
        String[] arr = { "陈奕迅,男", "钟汉良,男", "胡歌,男" };

        StringBuilder str = new StringBuilder();
        //完整写法:
        printInfo(arr, new Consumer<String>() {
            @Override
            public void accept(String o) {
                str.append(o.split(",")[0]+"-");
                System.out.print("name:"+o.split(",")[0]);
            }
        }, new Consumer<String>() {
            @Override
            public void accept(String o) {
                str.append(o.split(",")[1]+",");
                System.out.println(";sex:"+o.split(",")[1]);
            }
        });
        System.out.println(str);

        //lambda表达式写法
        //调用printInfo方法,传递一个字符串数组,和两个Lambda表达式
        printInfo(arr,(message)->{
            //消费方式:对message进行切割,获取姓名,按照指定的格式输出
            String name = message.split(",")[0];
            System.out.print("姓名: "+name);
        },(message)->{
            //消费方式:对message进行切割,获取年龄,按照指定的格式输出
            String age = message.split(",")[1];
            System.out.println(";性别: "+age+"。");
        });


        for (String i:arr) {
            new Consumer<String>() {
                @Override
                public void accept(String o) {
                    str.append(o.split(",")[0]+"-");
                    System.out.print("测试name:"+o.split(",")[0]);
                }
            }.andThen( new Consumer<String>() {
                @Override
                public void accept(String o) {
                    str.append(o.split(",")[1]+",");
                    System.out.println(";测试sex:"+o.split(",")[1]);
                }
            }).accept(i);
        }
    }
}
