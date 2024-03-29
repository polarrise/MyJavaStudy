package com.powersi.service.impl;

import com.powersi.annotation.PayCode;
import com.powersi.service.IPay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayService implements ApplicationListener<ContextRefreshedEvent> {

  private static Map<String, IPay> payMap = null;
  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
    Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PayCode.class);

    if (beansWithAnnotation != null) {
      payMap = new HashMap<>();
      beansWithAnnotation.forEach((key, value) ->{
        String bizType = value.getClass().getAnnotation(PayCode.class).value();
        payMap.put(bizType, (IPay) value);
      });
    }
  }
  public String pay(String code) {
    return payMap.get(code).pay();
  }
}
