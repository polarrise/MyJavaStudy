package com.tuling.mall.order.rpc;

import com.jinbiao.cloud.common.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fox
 */
@FeignClient(name = "jinbiao-user",path = "/jinbiao-user/user")
@Repository
public interface AccountFeignService {
    
    @RequestMapping("/debit")
    Boolean debit(@RequestParam("userId") Integer userId, @RequestParam("money") int money);
}
