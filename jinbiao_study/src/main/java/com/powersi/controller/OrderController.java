package com.powersi.controller;

import com.powersi.annotation.RequestRepeatIntercept;
import com.powersi.common.api.CommonResult;
import com.powersi.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author：Jinbiao
 * @Date：2023/4/17 16:06
 * @Desc：
 * 接口幂等性保证方案
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 10个并发请求，下了10次单
     * @param userId
     * @return
     */
    @PostMapping(value = "/create")
    public CommonResult createOrder(@RequestParam Long userId ) {
        return CommonResult.success(orderService.createOrder(userId));
    }

    /**
     * 分布式锁保证下单接口幂等性
     * @param userId
     * @return
     */
    @PostMapping(value = "/createOrderByRedisson")
    public CommonResult createOrderByRedisson(@RequestParam Long userId ) {
        return CommonResult.success(orderService.createOrderByRedisson(userId));
    }


    /**
     * 分布式锁+切面+自定义注解 集中管理接口幂等性
     * @param userId
     * @return
     */
    @PostMapping(value = "/createOrderByAspect")
    @RequestRepeatIntercept(value = "/order/createOrderByAspect")
    public CommonResult createOrderByAspect(@RequestParam Long userId ) {
        return CommonResult.success(orderService.createOrderByAspect(userId));
    }

    /**
     * redis+token机制 保证接口幂等性
     */
    @PostMapping(value = "/createByRedisToken")
    @RequestRepeatIntercept(value = "/order/create4")
    public CommonResult createByRedisToken(@RequestParam Long userId ) {
        return CommonResult.success(orderService.createByRedisToken(userId));
    }

    /**
     * create unique index orderUnque on oms_order(member_id, product_id);
     * 数据库唯一索引
     */
    @PostMapping(value = "/createByDbUniqueIndex")
    @RequestRepeatIntercept(value = "/order/create4")
    public CommonResult createByDbUniqueIndex(@RequestParam Long userId ) {
        return CommonResult.success(orderService.createByDbUniqueIndex(userId));
    }

    /**
     * 数据库乐观锁实现+版本号
     */
    @PostMapping(value = "/updateByDBVersion")
    @RequestRepeatIntercept(value = "/order/create4")
    public CommonResult updateByDBVersion(@RequestParam Long userId,@RequestParam Integer version ) {
        return CommonResult.success(orderService.updateByDBVersion(userId,version));
    }

}
