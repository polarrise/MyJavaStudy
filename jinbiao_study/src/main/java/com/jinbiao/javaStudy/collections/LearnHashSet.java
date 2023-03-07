package com.jinbiao.javaStudy.collections;


import java.util.HashSet;
import java.util.Objects;

/**
 * @author WangJinbiao
 * @date 2023/2/8 22:32
 * @desc
 */
public class LearnHashSet {
    public static void main(String[] args) {
        Student student1 = new Student("张三",18);
        Student student2 = new Student("张三",18);
        System.out.println(student1.equals(student2));
        HashSet<Student> hashSet = new HashSet<Student>();
        hashSet.add(student1);
        hashSet.add(student2);
        System.out.println(hashSet);
        
    }
}

class Student{
    private String name;
    private Integer age;

    public Student(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(age, student.age);
    }

    //@Override
    //public int hashCode() {
    //    return Objects.hash(name, age);
    //}

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
