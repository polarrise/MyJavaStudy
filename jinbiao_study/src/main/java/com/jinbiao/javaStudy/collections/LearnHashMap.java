package com.jinbiao.javaStudy.collections;
import java.util.HashMap;

/**
 * @author WangJinbiao
 * @date 2023/2/11 13:18
 * @desc 注意：Map添加的元素是无序的
 */
public class LearnHashMap {
    public static void main(String[] args) {
        HashMap<String, String>map=new HashMap<>();
        map.put("1", "111");
        map.put("2", "122");
        map.put("3", "133");
        map.put("4", "144");
        map.put("5", "155");
        map.put("null",null);
        for(String str:map.keySet()){
            System.out.println("key is "+str+" value is "+map.get(str));
        }
    }
}
