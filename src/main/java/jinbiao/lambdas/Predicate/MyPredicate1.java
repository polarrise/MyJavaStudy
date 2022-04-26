package jinbiao.lambdas.Predicate;

import java.util.function.Predicate;

/**
 ③断定类型：Predicate<T>接口
 特点：boolean类型判断，作为方法/构造参数
 java.util.function.Predicate<T>接口
 作用:对某种数据类型的数据进行判断,结果返回一个boolean值

 Predicate接口中包含一个抽象方法：
 boolean test(T t):用来对指定数据类型数据进行判断的方法
 结果:
 符合条件,返回true
 不符合条件,返回false
 */
public class MyPredicate1 {
    public static boolean validateStr(String str, Predicate<String> predicate){
        return predicate.test(str);
    }

    public static void main(String[] args) {
        String str = "abcdef";
        //完整写法:
        boolean b1 = validateStr(str, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length()>5;
            }
        });
        System.out.println(b1);

        //lambda表达式写法
        boolean b = validateStr(str,s->s.length()>5);   //lambda表达式去调用test方法,把str作为入参传进test方法
        System.out.println(b);


//        main方法体/一般方法体写法:
        boolean test = new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return str.length() > 5;
            }
        }.test(str);
        System.out.println("方法体测试:"+test);
    }

}
