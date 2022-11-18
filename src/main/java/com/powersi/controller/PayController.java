package com.powersi.controller;


import com.powersi.common.api.CommonResult;
import com.powersi.service.impl.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testPay")
@Api(value = " Pay类Api文档",tags={"查询PayController"})
public class PayController {

    @Autowired
    private PayService  payService;

    @RequestMapping("toPay")
    @ApiOperation(value="发起支付", notes="根据支付方式发起对应的支付")
    public CommonResult<String> toPay(String payWay){
        payService.pay(payWay);
        return CommonResult.success();
    }

}
