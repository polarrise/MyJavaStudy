package com.jinbiao.HelperClass;

import lombok.Data;

import java.util.*;

public class OptionalTest {
    public Person test(){
        Person person=new Person();
        if (Optional.ofNullable(person).isPresent()){
            //写不为空的逻辑
            person.setName("Jinbiao");
            person.setAge(2);
            System.out.println("person不为空");
            Person person1 = Optional.ofNullable(person).get();
            System.out.println(person1);
            return person1;
        }else{
            //写为空的逻辑
            System.out.println("person为空");
            return null;
        }
    }
    public static void main(String[] args) {
        //Person test = new OptionalTest().test();
        //
        //List list=new ArrayList<String>();
        //list.get(0);

        Map map=new HashMap<>();
        System.out.println( map.get(""));
    }
}

@Data
class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}