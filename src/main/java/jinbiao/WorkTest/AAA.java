package jinbiao.WorkTest;
import com.alibaba.fastjson.JSONObject;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AAA {

    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>();
        Cat cat1 = new Cat();
        cat1.setAge(2);
        cat1.setName("中华狸猫");

        Cat cat2 = new Cat();
        cat2.setAge(1);
        cat2.setName("波斯猫");

        Cat cat3 = new Cat();
        cat3.setAge(3);
        cat3.setName("英国短毛猫");

        Cat cat4 = new Cat();
        cat4.setAge(2);
        cat4.setName("异国短毛猫（加菲）");

        Cat cat5 = new Cat();
        cat5.setAge(5);
        cat5.setName("布偶猫");

        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        cats.add(cat4);
        cats.add(cat5);
        String s = JSONObject.toJSONString(cats);
        System.out.println("汉字排序前的顺序为======:" + s);
        Comparator<Object> com= Collator.getInstance(java.util.Locale.CHINA);
        cats.sort((o1, o2) -> com.compare(o1.getName(), o2.getName()));
        String s1 = JSONObject.toJSONString(cats);

        System.out.println("汉字排序后的顺序为******:" + s1);
    }

    static class Cat {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
