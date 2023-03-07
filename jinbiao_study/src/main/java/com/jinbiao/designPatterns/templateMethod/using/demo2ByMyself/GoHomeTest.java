package com.jinbiao.designPatterns.templateMethod.using.demo2ByMyself;

/**
 * @Author：Jinbiao
 * @Date：2023/3/6 19:25
 * @Desc：回家出行的业务场景的客户端测试类
 */
public class GoHomeTest {
    public static void main(String[] args) {

        GoHome goHomeByBus = new GoHomeByBus("Roommate","长沙");
        GoHome goHomeByHighSpeedRailway = new GoHomeByHighSpeedRailway("Jinbiao","韶山南");
        goHomeByBus.templateMethod();
        System.out.println();
        System.out.println();
        goHomeByHighSpeedRailway.templateMethod();
    }
}
