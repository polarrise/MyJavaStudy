package com.jinbiao.lambdas.Stream.API;

import java.util.stream.Stream;

public class StreamMap {
    public static void main(String[] args) {

        String [] arr={"1","2","3"};
        Stream<String> arr1 = Stream.of(arr);
        Stream<Integer> integerStream = arr1.map(a -> Integer.valueOf(a));

        Stream<String> stream = Stream.of("1", "2", "3");
        Stream<Integer> integerStream2 = stream.map(str -> Integer.parseInt(str));
        integerStream.forEach(i-> System.out.println(i));
    }
}
