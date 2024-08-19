package com.powersi.controller.manyThreadTransaction;

/**
 * @author WangJinbiao
 * @date 2024/08/19 21：11
 * 测试相关Controller
 */

import com.jinbiao.cloud.common.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot中的多线程事务处理太繁琐？一个自定义注解直接搞定！
 */
@RequestMapping("/manyThread")
@RestController
@Slf4j
public class TestTransactionController {

    @Autowired
    private MainService mainService;

    @PostMapping("/transactionTest")
    public CommonResult get() {
        mainService.test1();
        return CommonResult.success("请求成功!");
    }
}
