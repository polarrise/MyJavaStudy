package com.jinbiao.spring_study.circularReferences.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author wangjinbiao
 * @date 2023/2/17 14:26
 * @desc
 */
@Component
public class A {

  private String name ="Jinbiao";

  @Autowired
  private B b;

  //@Lazy   //在构造方法上@Lazy注解解决构造方法注入的循环依赖
  //public A(B b){
  //  this.b = b;
  //}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
