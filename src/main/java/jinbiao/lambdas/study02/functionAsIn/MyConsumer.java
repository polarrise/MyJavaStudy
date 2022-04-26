package jinbiao.lambdas.study02.functionAsIn;

import java.util.function.Consumer;

/**
 * 消费类型：Consumer<T>接口
 * 特点：只进不出，作为方法/构造参数
 * java.util.function.Consumer<T>接口则正好与Supplier接口相反，
 * 它不是生产一个数据，而是消费一个数据，其数据类型由泛型决定。
 * Consumer接口中包含抽象方法void accept(T t)，意为消费一个指定泛型的数据。
 *
 * Consumer接口是一个消费型接口,泛型执行什么类型,就可以使用accept方法消费什么类型的数据
 * 至于具体怎么消费(使用),需要自定义
 */
public class MyConsumer {
    public static void method(String name, Consumer<String> consumer){
        consumer.accept(name);
    }

    public static void main(String[] args) {
        method("小哇", new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s是："+s);
            }
        });

        method("小哇",(name)->{
            String s = new StringBuffer(name).reverse().toString();
            System.out.println("s是："+s);
        });
    }
}
