package com.jinbiao.designPatterns.templateMethod.using.demo1;

/**
 * @Author：Jinbiao
 * @Date：2023/3/6 18:57
 * @Desc：出国留学业务场景的客户端测试类
 */
public class StudyAbroadTest {

    public static void main(String[] args) {
        StudyAbroad tm = new StudyInAmerica();
        tm.TemplateMethod();

        System.out.println();
        System.out.println();

        StudyAbroad tm1 = new StudyInEngland();
        tm1.TemplateMethod();
    }

}
