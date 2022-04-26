package jinbiao.dataStruct_study.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 描述
 我们规定乘坐公共交通工具时，正常情况下乘客按序列号 number 的升序顺序排队就座，
 当乘客中有年龄 age 小于 5 岁（不含 5 岁）的幼儿乘客或是年龄 age 大于 60 岁（不含 60 岁）的老年人乘客时，
 优先按序就座。现在给你一个乘客列表，请你安排他们按序就座，结果请通过调用 System.out.println 方法进行输出。
 */

/**
 * 样例一  输入：
 * Jack 21
 * Jimi 69
 * July 52
 * July 42
 * Jane 70
 * 输出：
 * number = 1, name = Jimi, age = 69
 * number = 4, name = Jane, age = 70
 * number = 0, name = Jack, age = 21
 * number = 2, name = July, age = 52
 * number = 3, name = July, age = 42
 */
public class Solution {
    /**
     * 解题思路
     * 1.第一步完成对列表list循环遍历，创建存储结果的列表。
     * 2.第二步筛选符合条件的list元素add进结果列表中，以0开始每次对符合条件的元素下标加1，最后add剩余元素。
     * 3.第三步循环输出结果列表。
     */
    public void priority(List<Person> list) {
         Queue<Person>reQueue=new LinkedList<>();
         Queue<Person>queue=new LinkedList<>();

        list.forEach(person->{
            if(person.age<5||person.age>60){
                reQueue.add(person);
            }else{
                queue.add(person);
            }
        });

        queue.stream().forEach(a->{
            reQueue.offer(a);
        });
        reQueue.stream().forEach(a->{
            System.out.println(a);
        });


    }

    public static void main(String[] args) {
        Person person1=new Person("Jack",21,0);
        Person person2=new Person("Jimi",69,1);
        Person person3=new Person("July",52,2);
        Person person4=new Person("July",42,3);
        Person person5=new Person("Jane",70,4);
        List<Person> list=new ArrayList();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        new Solution().priority(list);
    }

}


class Person {
    String name;
    int age;
    int number;
    public Person(String name, int age, int number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }
    public String toString() {
        return "number = " + number + ", name = " + name + ", age = " + age;
    }
  }

