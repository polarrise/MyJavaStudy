package com.tuling.mall.user.rpc;

import com.jinbiao.cloud.common.entity.CommonResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author：Jinbiao
 * @Date：2023/4/26 18:21
 * @Desc：
 */
@Component
public class OrderFeignServiceFactory implements FallbackFactory<OrderFeignService> {
    @Override
    public OrderFeignService create(Throwable cause) {
        // 与fallback的方式类似 需要返回一个对应接口的对象
        return new OrderFeignService() {
            @Override
            public CommonResult findOrderByUserId(Integer userId) {
                return CommonResult.success("服务降级了===");
            }
        };
    }
}
