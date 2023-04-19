package com.tuling.stock.controller;

import com.tuling.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fox
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    //@Autowired
    //private StockServiceByTCC stockServiceByTCC;

    @RequestMapping(path = "/deduct")
    public Boolean deduct(Integer productId, Integer count) {
        // 扣减库存
        Boolean deduct = stockService.deduct(productId, count);
        return deduct;
    }

    //@RequestMapping(path = "/deductByTCC")
    //public Boolean deductByTCC(String productId, Integer count) {
    //    // 扣减库存
    //    Boolean deduct = stockServiceByTCC.deduct(productId, count);
    //    return deduct;
    //}
}
