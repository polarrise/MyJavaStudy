package com.jinbiao.designPatterns.templateMethod.using.demo2ByMyself;

/**
 * @Author：Jinbiao
 * @Date：2023/3/6 18:59
 * @Desc：以放假回家来举例模板方法。

 * 优点：
 * 封装不变部分，扩展可变部分
 * 提取公共部分代码，便于维护
 * 行为由父类控制，子类实现
 *
 * 缺点：
 * 按照我们的设计习惯，抽象类负责声明最抽象、最一般的事物属性和方法，实现类完成具体的事物属性和方法。但是模板方法模式却颠倒了，抽象类定义了部分抽象方法，
 * 由子类实现，子类执行的结果影响了父类的结果，也就是子类对父类产生了影响，这在复杂的项目中，会带来代码阅读的难度，而且也会让新手产生不适感。
 */
public abstract class GoHome {

    // 选择回家出行的交通工具
    public abstract void chooseVehicle();

    // 去站点的方式
    public abstract void toStationWay();

    // 刷身份证验票进站
    public void CheckTicketEnterStation(){
        System.out.println("刷身份证验票进站===");
    };

    //到达
    public abstract void arrival();

    // 模板方法
    public void templateMethod() {
        // 选择回家出行的交通工具
        chooseVehicle();
        // 去站点的方式
        toStationWay();
        // 刷身份证验票进站
        CheckTicketEnterStation();
        // 到目的地
        arrival();
    }
}
