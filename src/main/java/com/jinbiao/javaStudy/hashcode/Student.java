package com.jinbiao.javaStudy.hashcode;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author wangjinbiao
 * @date 2023/2/8 15:16
 * @desc
 */
public class Student {

  private String name;

  private int age;

  public Student(String name,int age){
    this.name = name;
    this.age = age;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Student)) return false;
    Student student = (Student) o;
    return age == student.age &&
        Objects.equals(name, student.name);
  }
  @Override
  public int hashCode() {
    return Objects.hash(name, age);
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }

  public static void main(String[] args) {
    Student s1=new Student("小明",18);
    Student s2=new Student("小明",18);
    System.out.println("s1.hashCode:"+s1.hashCode());
    System.out.println("s2.hashCode:"+s2.hashCode());
    System.out.println(s1.equals(s2));

    HashMap<Object, Object> hashMap = new HashMap<>();
    hashMap.put(s1,s1);
    hashMap.put(s2,s2);
    System.out.println(hashMap);
  }
}
