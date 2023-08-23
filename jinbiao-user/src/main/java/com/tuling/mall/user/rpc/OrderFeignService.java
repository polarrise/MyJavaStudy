package com.tuling.mall.user.rpc;

import com.jinbiao.cloud.common.entity.CommonResult;
import com.tuling.mall.user.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author：Jinbiao
 * @Date：2023/4/10 13:49
 * @Desc：configuration:配置日志级别查看请求url,响应状态码，响应时间等等，configuration针对某一个微服务上的配置
 */
@FeignClient(value = "jinbiao-order",path = "/jinbiao-order/order", fallbackFactory = OrderFeignServiceFactory.class)  // 使用yml的配置，需要注释掉FeignConfig里面的配置bean. fox老师建议用yml来使用配置中心集中管理
//@FeignClient(value = "jinbiao-order",path = "/jinbiao-order/order",configuration = FeignConfig.class,fallbackFactory = OrderFeignServiceFactory.class) // java bean的方式配置
public interface OrderFeignService {
    @RequestMapping("/findOrderByUserId/{userId}")
    CommonResult findOrderByUserId(@PathVariable("userId") Integer userId);

    /**
     * url传参，要使用@RequestParam
     * 多个url传参可以使用SpringQueryMap +定义一个对象 。
     */
}

