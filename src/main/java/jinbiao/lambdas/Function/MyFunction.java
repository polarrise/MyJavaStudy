package jinbiao.lambdas.Function;

import java.util.function.Function;

/**
 ④转换类型：Function<T>接口
 特点：apply方法有输入，有输出
 java.util.function.Function<T,R>接口用来根据一个类型的数据得到另一个类型的数据，
 前者称为前置条件，后者称为后置条件。

 Function接口中最主要的抽象方法为：R apply(T t)，根据类型T的参数获取类型R的结果。
 使用的场景例如：将String类型转换为Integer类型。
 */
public class MyFunction {
    public static void change(String str, Function<String,Integer> function){
//        Integer i = function.apply(str);
        //自动拆箱 Integer自动转为int
        int i = function.apply(str);
        System.out.println(i);
    }

    public static void main(String[] args) {
        String s = "1234";
        change(s,str->Integer.parseInt(str));

        int i = Integer.parseInt(s);
        System.out.println(i);

        Integer apply = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        }.apply(s);
        System.out.println(apply);
    }
}
