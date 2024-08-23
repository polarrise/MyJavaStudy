package com.jinbiao.spring_study.circularReferences.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjinbiao
 * @date 2023/2/15 16:57
 * @desc
 */
@Service
public class BService {
  @Autowired
  private AService aService;

  public void testB(){
    System.out.println("BService方法执行啦");
  }
}
