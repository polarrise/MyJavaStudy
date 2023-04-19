package com.tuling.mall.order.service.impl;


import com.jinbiao.cloud.mbg.model.TOrder;
import com.tuling.mall.order.enums.OrderStatus;
import com.tuling.mall.order.mapper.OrderMapper;
import com.tuling.mall.order.service.OrderServiceByTCC;
import com.tuling.mall.order.vo.OrderVo;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
@Slf4j
public class OrderServiceByTCCImpl implements OrderServiceByTCC {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    @Transactional(rollbackFor=Exception.class)
    public TOrder prepareSaveOrder(OrderVo orderVo,@BusinessActionContextParameter(paramName = "orderId") Long orderId) {

        // 保存订单
        TOrder order = new TOrder();
        order.setId(orderId);
        order.setUserId(orderVo.getUserId());
        order.setProductId(orderVo.getProductId());
        order.setCount(orderVo.getCount());
        order.setMoney(new BigDecimal(orderVo.getMoney()));
        order.setStatus(OrderStatus.INIT.getValue());
        Integer saveOrderRecord = orderMapper.insert(order);
        log.info("保存订单{}", saveOrderRecord > 0 ? "成功" : "失败");

        return order;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {


        // 获取订单id
        long orderId = Long.parseLong(actionContext.getActionContext("orderId").toString());
        //更新订单状态为支付成功
        Integer updateOrderRecord = orderMapper.updateOrderStatus(orderId,OrderStatus.SUCCESS.getValue());
        log.info("更新订单id:{} {}", orderId, updateOrderRecord > 0 ? "支付成功" : "二阶段提交未成功");

        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {

        //获取订单id
        long orderId = Long.parseLong(actionContext.getActionContext("orderId").toString());
        //更新订单状态为支付失败
        Integer updateOrderRecord = orderMapper.updateOrderStatus(orderId, OrderStatus.FAIL.getValue());
        log.info("更新订单id:{} {}", orderId, updateOrderRecord > 0 ? "状态为支付失败" : "二阶段回滚未成功");

        return true;
    }


}