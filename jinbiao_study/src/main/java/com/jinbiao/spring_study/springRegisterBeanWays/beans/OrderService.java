package com.jinbiao.spring_study.springRegisterBeanWays.beans;

import org.springframework.stereotype.Component;

/**
 * @Author：Jinbiao
 * @Date：2023/4/11 21:43
 * @Desc：使用注解+Component注册bean
 *
 * 第1种方式： 使用@Component注解 + @ComponentScan包扫描方式
 */
@Component
public class OrderService {
    public void test(){
        System.out.println("打印test信息");
    }
}
