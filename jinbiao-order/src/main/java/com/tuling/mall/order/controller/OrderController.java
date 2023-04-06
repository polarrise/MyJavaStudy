package com.tuling.mall.order.controller;


import com.jinbiao.cloud.common.entity.CommonResult;
import com.tuling.mall.order.entity.OrderEntity;
import com.tuling.mall.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:46:19
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据用户id查询订单信息
     * @param userId
     * @return
     */
    @RequestMapping("/findOrderByUserId/{userId}")
    public CommonResult findOrderByUserId(@PathVariable("userId") Integer userId) {

        //模拟异常
        if(userId == 5){
            throw new IllegalArgumentException("非法参数异常");
        }
        //模拟超时
        if(userId == 6){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("根据userId:"+userId+"查询订单信息");
        List<OrderEntity> orderEntities = orderService.listByUserId(userId);
        return CommonResult.success(orderEntities);
    }

}
