package com.jinbiao.lambdas.Stream.API;

import java.util.stream.Stream;

/**
 * 根据数组获取流
 * 如果使用的不是集合或映射而是数组，由于数组对象不可能添加默认方法，所以 Stream 接口中提供了静态方法of ：
 * 总结：
 * 所有的 Collection 集合都可以通过stream默认方法获取流；
 * Stream 接口的静态方法of可以获取数组对应的流
 */
public class GetStreamFromArray {
    public static void main(String[] args) {
        String[] array = {"陈奕迅","钟汉良","杨千嬅"};
        Stream<String> stream = Stream.of(array);
        stream.filter(a-> a.startsWith("陈")).filter(a->a.contains("迅")).forEach(a-> System.out.println(a));
    }
}
