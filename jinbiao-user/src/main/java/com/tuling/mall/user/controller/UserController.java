package com.tuling.mall.user.controller;


import com.jinbiao.cloud.common.entity.CommonResult;
import com.tuling.mall.user.rpc.OrderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



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

}
