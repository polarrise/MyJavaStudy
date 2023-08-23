package com.tuling.mall.order.controller;


import com.jinbiao.cloud.common.entity.CommonResult;
import com.jinbiao.cloud.mbg.model.TOrder;
import com.tuling.mall.order.entity.OrderEntity;
import com.tuling.mall.order.service.BussinessService;
import com.tuling.mall.order.service.OrderService;
import com.tuling.mall.order.service.OrderServiceByTCC;
import com.tuling.mall.order.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


    @Autowired
    private BussinessService bussinessService;

    /**
     * 根据用户id查询订单信息
     * @param userId
     * @return
     */
    @RequestMapping("/findOrderByUserId/{userId}")
    public CommonResult findOrderByUserId(@PathVariable("userId") Integer userId,HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        System.out.println("authorization:"+authorization);
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

    @PostMapping("/createOrder")
    public CommonResult saveOrder(@RequestBody OrderVo orderVo){
        log.info("收到下单请求,用户:{}, 商品编号:{}", orderVo.getUserId(), orderVo.getProductId());
        TOrder order = orderService.saveOrder(orderVo);
        return CommonResult.success(order);
    }

    @PostMapping("/createOrderByTCC")
    public CommonResult createOrderByTCC(@RequestBody OrderVo orderVo){
        log.info("收到下单请求,用户:{}, 商品编号:{}", orderVo.getUserId(), orderVo.getProductId());
        TOrder order = bussinessService.saveOrder(orderVo);
        return CommonResult.success(order);
    }

    @GetMapping("/testgateway")
    public CommonResult<String> testGateway(HttpServletRequest request){
        log.info("gateWay获取请求头X-Request-info："
                +request.getHeader("X-Request-info"));
        return CommonResult.success("网关配置了filter：AddRequestHeader,订单服务添加了添加请求头："+request.getHeader("X-Request-info"));
    }

    @GetMapping("/testgateway2")
    public CommonResult<String> testGateway(@RequestHeader("X-Request-info") String color){
        log.info("gateWay获取请求头X-Request-info："+color);
        return CommonResult.success("网关配置了filter：AddRequestHeader,订单服务添加了添加请求头："+color);
    }

    /**
     * 获取网关的过滤器工厂中的请求参数color
     * @param color
     * @return
     * @throws Exception
     */
    @GetMapping("/testgateway3")
    public CommonResult<String> testGateway3(@RequestParam("color") String color){
        log.info("gateWay获取请求参数color:"+color);
        return CommonResult.success("订单服务获取gateWay传的请求参数color:"+color);
    }
}
