package com.jinbiao.designPatterns.templateMethod.using.demo1;

/**
 * @Author：Jinbiao
 * @Date：2023/3/6 18:54
 * @Desc：抽象类: 出国留学模板方法
 */
public abstract class StudyAbroad {
    // 模板方法
    public void TemplateMethod() {
        // 索取学校资料
        lookingForSchool();
        // 入学申请
        applyForEnrol();
        // 办理因私出国护照、出境卡和公证
        applyForPassport();
        // 申请签证
        applyForVisa();
        // 体检、订机票、准备行装
        readyGoAbroad();
        // 抵达
        arriving();
    }

    public void applyForPassport() {
        System.out.println("三.办理因私出国护照、出境卡和公证：");
    }

    public void applyForVisa() {
        System.out.println("四.申请签证：");
    }

    public void readyGoAbroad() {
        System.out.println("五.体检、订机票、准备行装：");
    }

    // 索取学校资料
    public abstract void lookingForSchool();

    // 入学申请
    public abstract void applyForEnrol();

    // 抵达
    public abstract void arriving();
}

