package jinbiao.lambdas.study01.YesInYesOut.second;


import java.util.Arrays;
import java.util.Comparator;

public class MyArrays {
    public static void main(String[] args) {
        Person[] arr = {new Person("陈奕迅",40),
                new Person("钟汉良",39),
                new Person("杨千嬅",38)};

        //对年龄进行排序
        Arrays.sort(arr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge()-o2.getAge();
            }
        });
    }
}
