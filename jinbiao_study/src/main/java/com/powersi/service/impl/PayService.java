package com.powersi.service.impl;

import com.powersi.annotation.PayCode;
import com.powersi.service.IPay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayService implements ApplicationListener<ContextRefreshedEvent> {

  // private static Map<String, IPay> payMap = null;  之前生产遇到过根据code来获取对应实现为null的情况，没搞懂payMap为什么会被清空？

  private static Map<String, IPay> payMap = new HashMap<>(16);

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
    Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PayCode.class);

    /**
     * 之前生产这种写法,payMap初始定义为null,遇到过根据code来获取对应实现为null的情况，没搞懂payMap为什么会被清空？
    if (beansWithAnnotation != null) {
        payMap = new HashMap<>();
        beansWithAnnotation.forEach((key, value) ->{
          String bizType = value.getClass().getAnnotation(PayCode.class).value();
          payMap.put(bizType, (IPay) value);
        });
    }**/

    if (!CollectionUtils.isEmpty(beansWithAnnotation)) {
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
