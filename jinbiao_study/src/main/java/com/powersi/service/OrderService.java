package com.powersi.service;

/**
 * @Author：Jinbiao
 * @Date：2023/4/17 16:06
 * @Desc：
 */
public interface OrderService {

    String createOrder(Long userId);

    String createOrderByRedisson(Long userId);

    String createOrderByAspect(Long userId);

    String createByRedisToken(Long userId);

    String createByDbUniqueIndex(Long userId);

    String updateByDBVersion(Long userId,Integer version);
}
