package com.tuling.mall.user.rpc;

import com.jinbiao.cloud.common.entity.CommonResult;
import com.tuling.mall.user.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author：Jinbiao
 * @Date：2023/4/10 13:49
 * @Desc：configuration:配置日志级别查看请求url,响应状态码，响应时间等等
 */
@FeignClient(value = "jinbiao-order",path = "/jinbiao-order/order",configuration = FeignConfig.class,fallbackFactory = OrderFeignServiceFactory.class)
public interface OrderFeignService {
    @RequestMapping("/findOrderByUserId/{userId}")
    CommonResult findOrderByUserId(@PathVariable("userId") Integer userId);
}

