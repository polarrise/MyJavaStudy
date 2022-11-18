package com.powersi.test;

import com.powersi.service.impl.PayService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PayTest {
  @Resource
  private PayService payService;
  @Test
   void toPay(){
    String weixin = payService.pay("weixin");
    System.out.println(weixin);
  }
}
