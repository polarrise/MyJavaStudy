package com.tuling.stock.service;

public interface StockService {

    /**
     * 扣减库存
     * @param productId 商品编号
     * @param count 扣除数量
     */
    Boolean deduct(Integer productId, int count);
}