package com.tuling.storage.controller;

import com.tuling.storage.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jinbiao.cloud.common.entity.CommonResult;

/**
 * @author Fox
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    
    @Autowired
    private StockService storageService;
    
    @RequestMapping(path = "/deduct")
    public Boolean deduct(Integer productId, Integer count) {
        // 扣减库存
        Boolean deduct = storageService.deduct(productId, count);
        return deduct;
    }
}
