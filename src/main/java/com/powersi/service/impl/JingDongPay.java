package com.powersi.service.impl;

import com.powersi.annotation.PayCode;
import com.powersi.service.IPay;
import org.springframework.stereotype.Service;

@PayCode(value = "jingdong", name = "京东支付")
@Service
public class JingDongPay implements IPay {
  @Override
  public String pay() {
    return "===发起京东支付===";
  }
}
