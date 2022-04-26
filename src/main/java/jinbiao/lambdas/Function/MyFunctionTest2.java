package jinbiao.lambdas.Function;

import java.util.function.Function;

/**
 String str = "赵丽颖,20";
 * 1.将字符串截取数字年龄部分，得到字符串；
 * 2.将上一步的字符串转换成为int类型的数字；
 * 3.将上一步的int数字累加100，得到结果int数字。
 *
 * 注意：使用匿名内部类会编译后会多产生一个类，而使用lambda，底层是invokedynamic指令,不会有多余的类
 */
public class MyFunctionTest2 {
    public static int change(String str, Function<String,String> fun1, Function<String,Integer> fun2,
                             Function<Integer,Integer> fun3){
        return fun1.andThen(fun2).andThen(fun3).apply(str);
    }

    public static void main(String[] args) {
        int num = change("赵丽颖,32",str->str.split(",")[1],
                str->Integer.parseInt(str),
                i->i+100);
        System.out.println(num);

       //完整写法:
        int change = change("赵丽颖,32", new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str.split(",")[1];
            }
        }, new Function<String, Integer>() {
            @Override
            public Integer apply(String str) {
                return Integer.parseInt(str);
            }
        }, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer i) {
                return i + 100;
            }
        });
        System.out.println("完整写法:"+change);
    }
}
