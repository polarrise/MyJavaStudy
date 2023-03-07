package com.powersi.service.impl;

import com.powersi.annotation.PayCode;
import com.powersi.service.IPay;
import org.springframework.stereotype.Service;

@PayCode(value = "alia", name = "支付宝支付")
@Service
public class AliaPay implements IPay {
  @Override
  public String pay() {
    return "===发起支付宝支付===";

  }
}
