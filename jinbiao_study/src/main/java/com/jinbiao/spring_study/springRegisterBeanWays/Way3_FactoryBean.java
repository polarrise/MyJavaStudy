package com.jinbiao.spring_study.springRegisterBeanWays;

import com.jinbiao.spring_study.springRegisterBeanWays.beans.StockService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author：Jinbiao
 * @Date：2023/4/11 21:44
 * @Desc：
 *第3种方式：FactoryBean方式
 * FactoryBean是一个Bean，它允许我们自定义Bean的创建，主要有三个方法：
 * 1、getObject()：自定义Bean如何创建；
 * 2、getObjectType()：要注册的Bean的类型；
 * 3、isSingleton()：是否单例；
 */
@Component
public class Way3_FactoryBean implements FactoryBean<StockService> {
    @Override
    public StockService getObject() throws Exception {
        System.out.println("使用FactoryBean方式注册StockService");
        return new StockService();
    }

    @Override
    public Class<?> getObjectType() {
        return StockService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
