package jinbiao.lambdas.Consumer;

import java.util.function.Consumer;

/**
 andThen:
 Consumer接口的默认方法andThen
 作用:需要两个Consumer接口,可以把两个Consumer接口组合到一起,在对数据进行消费
 */
public class AndThen {
    public static void method(String s, Consumer<String> consumer1, Consumer<String> consumer2){
//        consumer1.accept(s);
//        consumer2.accept(s);
        //使用andThen方法,把两个Consumer接口连接到一起,在消费数据
        //con1连接con2,先执行con1消费数据,在执行con2消费数据
        consumer1.andThen(consumer2).accept(s);
    }

    public static void main(String[] args) {

        //完整写法:
        method("Hello", new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toUpperCase());
            }}, new Consumer<String>() {
            @Override
            public void accept(String s1) {
                System.out.println(s1.toUpperCase());
            }
        });

        //lambda表达式写法
        method("Hello",
                (t)-> System.out.println(t.toUpperCase()), //消费方式:把字符串转换为大写输出
                (t)-> System.out.println(t.toLowerCase()));//消费方式:把字符串转换为小写输出

        StringBuilder str=new StringBuilder();
        new Consumer<String>() {
            @Override
            public void accept(String s) {
                str.append(s.toUpperCase());
                System.out.println(s.toUpperCase());
            }
        }.andThen(new Consumer<String>() {
            @Override
            public void accept(String s) {
                str.append(s.toUpperCase());
                System.out.println(s.toUpperCase());
            }
        }).accept("Hello");
        System.out.println(str);
    }
}
