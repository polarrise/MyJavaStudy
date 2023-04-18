package com.tuling.mall.order.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fox
 */
@FeignClient(name="jinbiao-stock",path="/stock")
@Repository
public interface StorageFeignService {
    
    @RequestMapping(path = "/deduct")
    Boolean deduct(@RequestParam("productId") Integer productId,@RequestParam("count") Integer count);
    
}
