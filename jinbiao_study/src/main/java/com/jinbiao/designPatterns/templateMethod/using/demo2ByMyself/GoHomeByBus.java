package com.jinbiao.designPatterns.templateMethod.using.demo2ByMyself;

/**
 * @Author：Jinbiao
 * @Date：2023/3/6 19:14
 * @Desc：通过坐汽车回家
 */
public class GoHomeByBus extends GoHome{

    // 姓名
    private String name;

    // 目的地
    private String destination;

    public GoHomeByBus(String name,String destination) {
        this.name = name;
        this.destination = destination;
    }

    @Override
    public void chooseVehicle() {
        System.out.println(name+"选择坐汽车回家===");
    }

    @Override
    public void toStationWay() {
        System.out.println(name+"选择坐地铁去车站===");
    }

    @Override
    public void arrival() {
        System.out.println(name+"到达目的地==="+destination);
    }
}
