package com.jinbiao.HelperClass;

import lombok.Data;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class OptionalTest {
    public Person test(){
        Person person=new Person();
        if (Optional.ofNullable(person).isPresent()){
            System.out.println("person不为空");
            //写不为空的逻辑
            person.setName("Jinbiao");
            person.setAge(2);
            person.setFlag(true);
            Person person1 = Optional.ofNullable(person).get();
            System.out.println(person1);
            return person1;
        }else{
            //写为空的逻辑
            System.out.println("person为空");
            return null;
        }
    }

    public Person test2(){
        Person person = new Person("rise",24,true);
        Optional.ofNullable(person).map(p->{
            System.out.println("p不为空");
            p.setFlag(false);
            Person person1 = Optional.ofNullable(p).get();
            System.out.println(person1);
            return p;
        }).orElseGet(()->new Person());
        return person;
    }

    private Person filterPerson(){
        Person person = new Person("rise",24,false);
        Person filterPerson = Optional.ofNullable(person).filter(p -> {
            if (p.getFlag()) {
                return true;
            }
            return false;
        }).orElse(new Person());
        System.out.println("过滤掉flag = false之后："+filterPerson);
        return filterPerson;
    }
    public static void main(String[] args) {
        OptionalTest optionalTest = new OptionalTest();
        optionalTest.test();

        Person person1 = new Person("rise1",23,true);
        Person person2 = new Person("rise2",24,false);
        //List<Person> personList = new ArrayList(){{ add(person1);add(person2); }};
        List<Person> personList = new ArrayList<Person>();
        Collections.addAll(personList, person1,person2);
        optionalTest.test2();

        optionalTest.filterPerson();

        List<Person> filterList = personList.stream().filter(person -> {
             if(person.getFlag().equals(true) && person.getName().equals("rise1")){
                 return true;
             }
            return false;
        }).collect(toList());

        System.out.println(filterList);

    }
}

@Data
class Person {
    private String name;
    private Integer age;
    private Boolean flag;
    public Person(String name, Integer age,Boolean flag) {
        this.name = name;
        this.age = age;
        this.flag = flag;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", flag=" + flag +
            '}';
    }
}