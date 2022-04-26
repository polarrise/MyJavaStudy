package jinbiao.lambdas.Function;

import java.util.function.Function;

/**
 1.String转Integer，加10
 *      Function<String,Integer> fun1 :Integer i = fun1.apply("123")+10;
 * 2.Interger转String
 *      Function<Integer,String> fun2 :String s = fun2.apply(i);
 *      Function<T,R>接口用来根据一个类型的数据得到另一个类型的数据，
 *      字符串转整形相加得到结果再传给2,2做类型转换 为字符串
 */
public class MyFunctionTest {
    public static void change(String str, Function<String,Integer> fun1, Function<Integer,String> fun2){
        String string = fun1.andThen(fun2).apply(str);
        System.out.println(string);
    }

    public static void main(String[] args) {
        change("123",str->Integer.parseInt(str)+10,i->i+"");  //lambda表达式去调用apply方法,把str作为入参传进apply方法


        change("123", new Function<String, Integer>() {
            @Override
            public Integer apply(String str) {
                System.out.println("str:"+str);
                return Integer.parseInt(str)+10;
            }
        }, new Function<Integer, String>() {
            @Override
            public String apply(Integer i) {
                System.out.println("i:"+i);
                return i+"";
            }
        });


    }
}
