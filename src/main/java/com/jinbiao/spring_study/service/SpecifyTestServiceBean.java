package com.jinbiao.spring_study.service;

import com.jinbiao.spring_study.service.testBeanToMany.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Author：Jinbiao
 * @Date：2023/3/3 23:10
 * @Desc：
 */
@Component
public class SpecifyTestServiceBean {

    /** 这样配置没指定bean名称是会出错的:  报错匹配到多个会报错，需要自己使用 @Autowired+@Qualifier指定使用哪个bean
     * Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'specifyTheBean':
     * Unsatisfied dependency expressed through field 'testService'; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException:
     * No qualifying bean of type 'com.jinbiao.spring_study.service.testBeanToMany.TestService' available: expected single matching bean but found 2: testService1,testService2
     */
    //@Autowired
    //private TestService testService;


    @Autowired
    @Qualifier("testService2")
    private TestService testService;

    public void findAll() {
        testService.findAll();
    }
}
