package com.tuling.mall.order.service.impl;


import com.tuling.mall.order.dao.OrderDao;
import com.tuling.mall.order.entity.OrderEntity;
import com.tuling.mall.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("orderService")
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderEntity> listByUserId(Integer userId) {
        return orderDao.listByUserId(userId);
    }

}