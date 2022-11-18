package com.jinbiao.lambdas.study01.YesInYesOut.second;

public class Person {
    private String name;

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

    private int age;

    public Person(String name,int age){
         this.name=name;
         this.age=age;
    }


}
