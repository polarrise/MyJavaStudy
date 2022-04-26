package jinbiao.lambdas.Stream.API;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class GetStreamFromMap {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();

        Stream<Map.Entry<String, String>> stream1 = map.entrySet().stream();

        Stream<String> stream2 = map.keySet().stream();
        Stream<String> stream3 = map.values().stream();

        String[] array = {"陈奕迅","钟汉良","杨千嬅"};
        Stream<String> arrayStream = Stream.of(array);
        Object[] array1 = arrayStream.filter(a -> a.startsWith("陈")).toArray();
        for (Object i:array1) {
            System.out.println(i);
        }

        String str="";
        System.out.println(str.trim().equals(""));
    }
}
