package com.tuling.mall.user.controller;


import com.jinbiao.cloud.common.entity.CommonResult;
import com.tuling.mall.user.rpc.OrderFeignService;
import com.tuling.mall.user.service.UserService;
import com.tuling.mall.user.service.UserServiceByTCC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


/**
 * 
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    OrderFeignService orderFeignService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceByTCC userServiceByTCC;


    @RequestMapping(value = "/findOrderByUserId/{id}")
    public CommonResult findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:"+id+"查询订单信息");
        // restTemplate调用,url写死
        //String url = "http://localhost:8020/jinbiao-order/order/findOrderByUserId/"+id;
        // ribbon实现，restTemplate需要添加@LoadBalanced注解
        // jinbiao-order  ip:port
        String url = "http://jinbiao-order/jinbiao-order/order/findOrderByUserId/"+id;

        CommonResult result = restTemplate.getForObject(url,CommonResult.class);
        return result;
    }

    /**
     * 通过openFeign远程服务调用
     * @param id
     * @return
     */
    @RequestMapping(value = "/findOrderByUserId2/{id}")
    public CommonResult findOrderByUserId2(@PathVariable("id") Integer id) {
        log.info("根据userId:"+id+"查询订单信息");
        CommonResult result = orderFeignService.findOrderByUserId(id);
        return result;
    }


    /**
     *
     * @param userId
     * @param money
     * @return['
     * @throws Exception
     */
    @RequestMapping("/debit")
    public Boolean debit(Integer userId, BigDecimal money){
        // 用户账户扣款
        userService.debit(userId, money);
        return true;
    }

    @RequestMapping("/debitByTCC")
    public Boolean userServiceByTCC(Integer userId, BigDecimal money) {
        // 用户账户扣款
        userServiceByTCC.debit(userId, money);
        return true;
    }

}
