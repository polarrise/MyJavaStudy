package com.jinbiao.designPatterns.templateMethod.using.demo1;

/**
 * @Author：Jinbiao
 * @Date：2023/3/6 18:54
 * @Desc：美国留学具体实现类
 */
public class StudyInAmerica extends StudyAbroad{

    @Override
    public void lookingForSchool() {
        System.out.println("一.索取学校以下资料(美国留学)：");
    }

    @Override
    public void applyForEnrol() {
        System.out.println("二.入学申请(美国留学)：");
    }

    @Override
    public void arriving() {
        System.out.println("六.抵达目标学校(美国留学)：");
    }

}
