package com.jinbiao.designPatterns.AbstractFactory;
/**抽象工厂类:
 * 抽象工厂模式提供一个创建一系列相关或相互依赖对象的接口，而无须指定他们具体的类。
 * 它针对的是有多个产品的等级结构。而工厂方法模式针对的是一个产品的等级结构。
 */
public abstract class AbstractFactory {
    public abstract Vehicle createVehicle();

    public abstract Weapon createWeapon();

    public abstract Food createFood();
}

abstract class Food{
    abstract void printName();
}

abstract class Vehicle{
    abstract void run();
}

abstract class Weapon{
    abstract void shoot();
}

abstract class Meet extends Food{
    abstract void eval();
}

class PigMeet extends Meet{
    @Override
    void printName() {
        System.out.println("猪肉");
    }
    @Override
    void eval() {
        System.out.println("味道好极了");
    }
}

class Apple extends Food{
    @Override
    void printName() {
        System.out.println("食物名称Apple--");
    }
}

class Car extends Vehicle{
    @Override
    void run() {
        System.out.println("Car跑起来了--");
    }
}

class AK47 extends Weapon{
    @Override
    void shoot() {
        System.out.println("AK47在射击--");
    }
}

//具体工厂类，其中Food,Vehicle，Weapon是抽象类，
class DefaultFactory extends AbstractFactory{
    @Override
//    public Food createFood() {
//        return new Apple();
//    }
    public Food createFood() {    //猪肉继承->抽象类Meet->继承抽象类Food
        return new PigMeet();
    }
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
    @Override
    public Weapon createWeapon() {
        return new AK47();
    }
}

//测试类
class Test {
    public static void main(String[] args) {
        AbstractFactory f = new DefaultFactory();
        Vehicle v = f.createVehicle();  //创建小车:Car
        v.run();
        Weapon w = f.createWeapon();   //创建装备:AK47
        w.shoot();
//        Food apple = f.createFood();     //创建食物:Apple
        Food a = f.createFood();     //创建食物:猪肉。   因为Food类是最上层的,父类引用(Food)指向子类对象(Food)是
        a.printName();
        System.out.println("==============");

        Meet meet=new PigMeet();
        meet.printName();
        meet.eval();
    }
}

/**
 * 抽象类与接口都不能直接new
 * 采用匿名内部类的方式。通过javap反编译可以发现也不是直接new的这个接口或者抽象类
 */