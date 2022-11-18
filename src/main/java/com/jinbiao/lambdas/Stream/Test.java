package com.jinbiao.lambdas.Stream;

import java.util.List;

/**
 * @author com.jinbiao
 * @date 2021/11/12
 * @apiNote
 */
public class Test {


    public static void main(String[] args) {
    //    List<Student> studentsList=new ArrayList();
    //
    //    List<Student> list  = studentsList.stream().filter(a -> a.getName().equals("rise")).collect(Collectors.toList());
    //    System.out.println(list);
    //
    //    Map<String, List<Student>> studentMap=new HashMap<>();
    //    Map<String, List<Student>> collect = studentsList.stream().filter(a -> !(a.getName().equals("100000"))).collect(Collectors.groupingBy(Student::getName));
    //    System.out.println(collect);
    //
    //    //遍历学生列表
    //    studentsList.forEach(a->{
    //
    //        if(collect.get(a.getName())==null){
    //            a.setHobbies(new ArrayList<>());
    //        }else {
    //
    //}
    //    });

        Integer a=null;
        if(1==a){
            System.out.println("a=1");
        }
    }
}

class  Student{
    private String name;
    private int age;
    private List hobbies;
    Student(String name,int age,List hobbies){
        this.name=name;
        this.age=age;
        this.hobbies=hobbies;
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

    public List getHobbies() {
        return hobbies;
    }

    public void setHobbies(List hobbies) {
        this.hobbies = hobbies;
    }
}
