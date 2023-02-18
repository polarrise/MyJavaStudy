package com.jinbiao.spring_study.circularReferences.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjinbiao
 * @date 2023/2/15 16:57
 * @desc
 * AService 创建生命周期：

  AService出现了循环依赖的情况下才需要提前AOP
  1.创建一个AService普通对象  -->?提前AOP-->AService代理对象 --->zhouyuMap<beanName,AService代理对象></>
  2.填充bService属性   -->去单例池中找BService-->创建BService的Bean对象
  3.填充其他属性
  4.其他操作
  5.初始化后(AOP)
  6.放入单例池

 * BService 创建生命周期：
  1.创建一个BService普通对象
  2.填充aService属性   -->去单例池中找AService-->creatingSet-->出现了循环依赖--提前AOP--AService代理对象
  3.填充其他属性
  4.其他操作
  5.初始化后
  6.放入单例池

 A a =new A();

 B b = new B();
 b.a = a半成品普通对象

 a.b = b
 */
@Service
public class AService {

  @Autowired
  private BService bService;
}
