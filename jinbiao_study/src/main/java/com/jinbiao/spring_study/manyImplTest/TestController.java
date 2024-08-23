package com.jinbiao.spring_study.manyImplTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * @Author：Jinbiao
 * @Date：2023/3/3 23:10
 * @Desc：
 */
@Controller
public class TestController {

    /** 这样配置没指定bean名称是会出错的:  报错匹配到多个会报错，需要自己使用 @Autowired+@Qualifier指定使用哪个bean
     * Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'specifyTheBean':
     * Unsatisfied dependency expressed through field 'testService'; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException:
     * No qualifying bean of type 'com.jinbiao.spring_study.service.testBeanToMany.TestService' available: expected single matching bean but found 2: testService1,testService2
     * Spring属性注入是先byType 再byName 如果byType有多个，byName又不对会报错。 byType只有一个的话，属性名称可以随便命名。
     */
    //@Autowired
    //private TestService testService;


    @Autowired
    @Qualifier("testServiceImpl1")
    private TestService testService;

    //@Resource
    //private TestService testService2;

    public void findAll() {
        testService.findAll();
    }
}
