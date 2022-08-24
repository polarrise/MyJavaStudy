package jinbiao.WorkTest;

import java.util.ArrayList;
import java.util.List;

public class BBB {
    public static void main(String[] args) {
        List<Integer> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add(i);
        }
        int maxIndex = 200;
        //注释掉的代码是解决方案：
        //    先判断集合大小，超过200就截取，不超过就按当前大小做显示。
        //maxIndex = strings.size() >= maxIndex ? maxIndex : strings.size();
        List<Integer> list = strings.subList(0, maxIndex);
        for (Integer integer : list) {
            System.out.println(integer);
        }


    }
}
