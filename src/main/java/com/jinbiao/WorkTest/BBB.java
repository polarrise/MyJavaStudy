package com.jinbiao.WorkTest;

import java.util.ArrayList;
import java.util.List;

public class BBB {
    public static void main(String[] args) {
        List<Integer> strings = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            strings.add(i);
        }
        int maxIndex = 200;
        int begin = 0;
        //注释掉的代码是解决方案：
        //    先判断集合大小，超过200就截取，不超过就按当前大小做显示。
        maxIndex = strings.size() >= maxIndex ? maxIndex : strings.size();

       for (int i=1; i<strings.size()/maxIndex; i=i++){
           List<Integer> list = strings.subList((i-1)*maxIndex, maxIndex);
           System.out.println(list);
       }


    }
}
