package com.jinbiao.spring_study.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author wangjinbiao
 * @date 2023/2/14 17:13
 * @desc
 */
@Aspect
@Component
public class JinBiaoAspect {

  @Before("execution(public void com.jinbiao.spring_study.service.UserServiceInferConstruct.test())")
  public void jinBiaoBefore(JoinPoint joinPoint){
    System.out.println("jinBiaoBefore执行==");
  }
}
