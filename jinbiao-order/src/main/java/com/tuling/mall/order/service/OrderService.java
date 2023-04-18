package com.tuling.mall.order.service;

import com.jinbiao.cloud.mbg.model.TOrder;
import com.tuling.mall.order.entity.OrderEntity;
import com.tuling.mall.order.vo.OrderVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:46:19
 */
public interface OrderService {


    List<OrderEntity>  listByUserId(Integer userId);

    TOrder saveOrder(OrderVo orderVo);
}

