package com.jinbiao.lambdas.Stream;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author com.jinbiao
 * @date 2021/11/12
 * @apiNote
 * 1.stream() / parallelStream():  最常用到的方法，将集合转换为流
 * 2.filter(T -> boolean) :保留 boolean 为 true 的元素,collect(toList()) 可以把流转换为 List 类型
 * 3.distinct() :去除重复元素，这个方法是通过类的 equals 方法来判断两个元素是否相等的
 * 4.sorted() / sorted((T, T) -> int) :如果流中的元素的类实现了 Comparable 接口，即有自己的排序规则，那么可以直接调用 sorted() 方法对元素进行排序，如 Stream<Integer>
 * 5.limit(long n) :返回前 n 个元素
 * 6.skip(long n) :去除前 n 个元素
 * 7.map(T -> R)  :将流中的每一个元素 T 映射为 R（类似类型转换）
 * 8.flatMap(T -> Stream<R>) :将流中的每一个元素 T 映射为一个流，再把每一个流连接成为一个流
 * 9.anyMatch(T -> boolean):流中是否有一个元素匹配给定的 T -> boolean 条件。是否存在一个 person 对象的 age 等于 20：
 * 10.allMatch(T -> boolean) 流中是否所有元素都匹配给定的 T -> boolean 条件
 */
public class StringOfStream {

    public static List reList(){
        List<Person> list = new ArrayList<>();
        list.add(new Person("jack", 20,"2022-4-20"));
        list.add(new Person("rise", 23,"2021-4-20"));
        list.add(new Person("mike", 22,"2020-4-20"));
        list.add(new Person("tom", 18,"2019-4-20"));
        return list;
    }
    public static void test1(){
        List<Person>list = reList();
        //2.filter(T -> boolean),保留 boolean 为 true 的元素
        list=list.stream().filter(person->person.getName().equals("rise")).collect(toList());   //collect(toList()) 可以把流转换为 List 类型
        System.out.println("1:"+list);
    }
    public static void test2(){
        List<Person>list = reList();
        //4.根据年龄大小来比较：
        list = list.stream().sorted((person1,person2)->person2.getAge()-person1.getAge()).collect(toList());
        System.out.println("2:"+list);

        //当然这个可以简化为
        list = list.stream().sorted(Comparator.comparingInt(Person::getAge)).collect(toList());
        System.out.println("3:"+list);

        //           respList= respList.stream().sorted(Comparator.comparing((UmsLawFirm::getLawFirmName))).collect(toList());  a-z排序
        //5.limit:
        list=list.stream().limit(2).collect(toList());  //limit(long n):返回前 n 个元素
        System.out.println("4:"+list);
        //6.skip:
        list=list.stream().skip(1).collect(toList());        //skip(long n):去除前 n 个元素
        System.out.println("5:"+list);
    }
    public static void test3() {
        List<Person>list = reList();
        //7.map(T ->R):newlist 里面的元素为 list 中每一个 Person 对象的 name 变量
        List<String> newlist = list.stream().map(person -> person.getName()).collect(toList()); //将流中的每一个元素 T 映射为 R（类似类型转换）
        System.out.println("6:"+newlist);

        List<String> list8 = new ArrayList<>();
        list8.add("aaa bbb ccc");
        list8.add("ddd eee fff");
        list8.add("ggg hhh iii");
        //8.split 方法返回的是 String[ ] 类型；所以我们需要使用 flatMap 方法，先使用Arrays::stream将每个 String[] 元素变成一个 Stream<String> 流，然后 flatMap 会将每一个流连接成为一个流，最终返回我们需要的 Stream<String>
        list8 = list8.stream().map(s -> s.split(" ")).flatMap(Arrays::stream).collect(toList());
        System.out.println("8:"+list8);

        //9.anyMatch(T -> boolean):流中是否有一个元素匹配给定的 T -> boolean 条件。是否存在一个 person 对象的 name 等于 rise
        List<Person>list2 = reList();
        boolean anyMatch = list2.stream().anyMatch(p -> p.getName().equals("rise"));
        System.out.println("9:"+anyMatch);

        //10.allMatch(T -> boolean) 流中是否所有元素都匹配给定的 T -> boolean 条件
        boolean allMatch = list2.stream().allMatch(p -> p.getAge() == 20);
        System.out.println("10:"+allMatch);

        //11.noneMatch(T -> boolean)流中是否没有元素匹配给定的 T -> boolean 条件
        boolean noneMatch1 = list2.stream().noneMatch(a -> a.getName().equals("rise"));  //list中是否 没有个名字叫做rise的人   ->有个叫做rise的人。  false
        boolean noneMatch2 = list2.stream().noneMatch(a -> a.getName().equals("shipu"));//list中是否 没有个名字叫做shipu的人   ->true
        System.out.println("11:"+noneMatch1);
        System.out.println("11:"+noneMatch2);

        //12.findAny() 和 findFirst()
        //findAny()：找到其中一个元素 （使用 stream() 时找到的是第一个元素；使用 parallelStream() 并行时找到的是其中一个元素）
        //findFirst()：找到第一个元素
        //值得注意的是，这两个方法返回的是一个 Optional<T> 对象，它是一个容器类，能代表一个值存在或不存在，这个后面会讲到
        Optional<Person> first = list2.stream().findFirst();
        System.out.println("12:"+first);
        Optional<Person> any = list2.stream().findAny();
        System.out.println("12:"+any);

        //13.reduce((T, T) -> T) 和 reduce(T, (T, T) -> T) 用于组合流中的元素，如求和，求积，求最大值等
        //计算年龄总和：  reduce 第一个参数 0 代表起始值为 0，lambda (a, b) -> a + b 即将两值相加产生一个新值
        Integer sum1 = list2.stream().map(Person::getAge).reduce(0,(a, b)->a+b);
        System.out.println("13:"+sum1);
        //与之相同:
        Integer sum2 = list2.stream().map(Person::getAge).reduce(0, Integer::sum);
        System.out.println("13:"+sum2);
        //计算年龄总乘积：
        int sum3 = list2.stream().map(Person::getAge).reduce(1, (a, b) -> a * b);
        System.out.println("13:"+sum3);
        Optional<Integer> sum4 = list2.stream().map(Person::getAge).reduce(Integer::sum);
        System.out.println("13:"+sum4);

        //14.count()  返回流中元素个数，结果为 long 类型
        long count = list2.stream().count();
        System.out.println("14:"+count);

        //15.collect()收集方法，我们很常用的是 collect(toList())，当然还有 collect(toSet()) 等，参数是一个收集器接口，这个后面会另外讲


        //16.forEach()返回结果为 void，很明显我们可以通过它来干什么了，比方说：
        list2.stream().forEach(p->{
                System.out.println("16:"+p.getName()+"-"+p.getAge());
        }
        );

    }
        public static void main(String[] args) {
        Map<Long,String>map=new HashMap<>();
            map.put(393429L,"17673635094");
            map.put(393429L,"17673635093");
            System.out.println(map);
        StringOfStream.test1();
        StringOfStream.test2();
        StringOfStream.test3();
    }

}

class Person {
    private String name;
    private int age;
    private String birth;
    public Person(String name,int age,String birth){
        this.name=name;
        this.age=age;
        this.birth=birth;
    }
    public Boolean equals(Person p1,Person p2){
        if(p1.getName().equals(p2.getName())&&p1.getAge()==p2.getAge()){
            return true;
        }else{
            return false;
        }
    }
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
