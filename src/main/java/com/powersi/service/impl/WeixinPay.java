package com.powersi.service.impl;

import com.powersi.annotation.PayCode;
import com.powersi.service.IPay;
import org.springframework.stereotype.Service;

@PayCode(value = "weixin", name = "微信支付")
@Service
public class WeixinPay implements IPay {
  @Override
  public void pay() {
    System.out.println("===发起微信支付===");
  }
}
