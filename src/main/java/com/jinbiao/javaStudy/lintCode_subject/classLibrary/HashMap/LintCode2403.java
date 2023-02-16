package com.jinbiao.javaStudy.lintCode_subject.classLibrary.HashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author com.jinbiao
 * @date 2022/2/10
 * @apiNote
 */
public class LintCode2403 {
    /**
     * 方案一:
     * 描述:请编写代码，使用 HashMap 中的相关方法输出 HashMap 集合所有数据的 Key 和 Value。
     */
    public void printHashMap(HashMap<Integer, String> map) {
        // write your code here
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, String> entry  = iterator.next();
            // 输出 Set 映射视图中的 key 和 value
            System.out.println("key: "+ entry.getKey() + "; " + "value: " + entry.getValue() + ";");
        }
    }

    /**
     * 方案二:需要使用循环语句来遍历集合中的数据通过 keySet 方法来获取 key，通过 get(key) 来获取值 value。
     */
    public void printHashMap2(HashMap<Integer, String> map) {
        // 使用增强 for 循环遍历 HashMap 集合中的 key
        Set<Integer> keySet = map.keySet();
        for (int i : map.keySet()) {
            System.out.println("key: " + i + "; value: " + map.get(i) + ";");
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "beijing");
        map.put(2, "shanghai");
        System.out.println(map);
        LintCode2403  solution = new LintCode2403();
        solution.printHashMap(map);
        solution.printHashMap2(map);
    }
}
