package com.jinbiao.designPatterns.adapter.v2;

/**
 * @author Jinbiao
 * @date 2022/4/25
 * @apiNote
 */
public class AdapterTest2 {
    public static void main(String[] args) {
        Adapter adapter=new Adapter();
        adapter.output5v();
    }
}

class Adaptee{
    public int output220v(){  //输出电压 220v
        return 220;
    }
}

interface Target{
    int output5v();       //目标电压， 比如我们的手机需要5V的电压
}

/**
 * Class Adapter 类的适配器模式,使用的是类的继承。   子类拥有了父类的功能
 */
class Adapter extends Adaptee implements Target{

    @Override
    public int output5v() {
        int i = output220v();        //子类继承父类,则子类拥有了父类的功能
        //.....
        System.out.println(String.format("原始电压: %d v  ->   输出电压:  %d v",i, 5));
        return 5;
    }
}
