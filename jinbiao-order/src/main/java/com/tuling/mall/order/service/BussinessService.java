package com.tuling.mall.order.service;

import com.jinbiao.cloud.mbg.model.TOrder;
import com.tuling.mall.order.vo.OrderVo;

/**
 * @Author Fox
 */
public interface BussinessService {

    /**
     * 保存订单
     */
    TOrder saveOrder(OrderVo orderVo) ;
}
