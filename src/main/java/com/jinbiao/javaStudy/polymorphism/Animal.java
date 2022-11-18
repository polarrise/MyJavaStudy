package com.jinbiao.javaStudy.polymorphism;

/**
 eg:  Animal动物类是父类, Cat是子类,Dog是子类。  动物都有个共同的行为(Common接口):会吠，吃。都用共同的属性:有嘴巴、眼睛、腿、毛发
 */
public class Animal implements Common{
    private String mouse;
    private String eye;
    private String foot;
    private String hair;


    @Override
    public void eat() {

    }

    @Override
    public void bark() {

    }

    public static void main(String[] args) {
        Animal cat=new Cat();   //父类引用指向子类对象
        cat.eat();
        cat.bark();

        Animal dog=new Dog();   //父类引用指向子类对象
        dog.eat();
        dog.bark();
    }
}
