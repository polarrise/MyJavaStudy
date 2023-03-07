package com.jinbiao.spring_study.circularReferences.demo;

import org.springframework.stereotype.Component;

/**
 * @author wangjinbiao
 * @date 2023/2/17 14:26
 * @desc
 */
@Component
public class B {

  private A a;

  public B(A a){
    this.a = a;
  }
}
