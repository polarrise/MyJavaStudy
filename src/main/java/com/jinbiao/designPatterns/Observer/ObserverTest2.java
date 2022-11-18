package com.jinbiao.designPatterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jinbiao
 * @date 2022/4/28
 * @apiNote
 */
public class ObserverTest2 {
    public static void main(String[] args) {
        Subject2 subject2 = new Subject2();
        Task1 task1 = new Task1();
        subject2.addObserver(task1);
        Task2 task2 = new Task2();
        subject2.addObserver(task2);

        subject2.notifyObserver2("新通知!!!");
        System.out.println("=====================");
        subject2.remove(task2);            //把观察者2移除了之后,就只有观察者1能够收到通知了~
        subject2.notifyObserver2("新通知!!!");

    }
}

/**
 * 观察者订阅的对象，也就是被观察者。 它的职责是:发生变化时,它的所有依赖者(Observer2)都会收到通知并更新
 */
class Subject2{
    //容器
    List<Observer2> container =new ArrayList<Observer2>();
    //add
    public void addObserver(Observer2 observer2){
        container.add(observer2);
    }
    //remove
    public void remove(Observer2 observer2){
        container.remove(observer2);
    }
    public void notifyObserver2(Object object){
        for (Observer2 item:container) {
            item.update(object);
        }
    }
}

interface Observer2{
     void update(Object object);
}

class Task1 implements  Observer2{
    @Override
    public void update(Object object) {
        System.out.println("task1 received: "+object);
    }
}

class Task2 implements  Observer2{
    @Override
    public void update(Object object) {
        System.out.println("task2 received: "+object);
    }
}