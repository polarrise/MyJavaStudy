package com.jinbiao.HelperClass;
import com.alibaba.fastjson.JSONObject;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class SortByFirstChinsesWord {

    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>();
        Cat cat1 = new Cat();
        cat1.setAge(2);
        cat1.setCnName("中华狸猫");
        cat1.setEnName("zhm");

        Cat cat2 = new Cat();
        cat2.setAge(1);
        cat2.setCnName("波斯猫");
        cat2.setEnName("bsm");

        Cat cat3 = new Cat();
        cat3.setAge(3);
        cat3.setCnName("英国短毛猫");
        cat3.setEnName("England");

        Cat cat4 = new Cat();
        cat4.setAge(2);
        cat4.setCnName("异国短毛猫（加菲）");
        cat4.setEnName("yiguo");

        Cat cat5 = new Cat();
        cat5.setAge(5);
        cat5.setCnName("布偶猫");
        cat5.setEnName("bou");

        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        cats.add(cat4);
        cats.add(cat5);
        System.out.println("汉字排序前的顺序为======:" + JSONObject.toJSONString(cats));
        Comparator<Object> com= Collator.getInstance(java.util.Locale.CHINA);
        cats.sort((o1, o2) -> com.compare(o1.getCnName(), o2.getCnName()));
        System.out.println("汉字排序后的顺序为:" + JSONObject.toJSONString(cats));

        System.out.println("英文字排序前的顺序为======:" + JSONObject.toJSONString(cats));
        Comparator<Object> com2= Collator.getInstance(Locale.ENGLISH);
        cats.sort((o1, o2) -> com2.compare(o1.getEnName(), o2.getEnName()));
        System.out.println("英文字排序后的顺序为:" + JSONObject.toJSONString(cats));
    }

    static class Cat {
        private String cnName;
        private String enName;
        private int age;

        public String getCnName() {
            return cnName;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Cat{" +
                "cnName='" + cnName + '\'' +
                ", enName='" + enName + '\'' +
                ", age=" + age +
                '}';
        }
    }
}
