package com.tuling.mall.order.service.impl;

import com.jinbiao.cloud.mbg.model.TOrder;
import com.tuling.mall.order.rpc.AccountFeignService;
import com.tuling.mall.order.rpc.StorageFeignService;
import com.tuling.mall.order.service.BussinessService;
import com.tuling.mall.order.service.OrderService;
import com.tuling.mall.order.util.UUIDGenerator;
import com.tuling.mall.order.vo.OrderVo;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Fox
 */
@Service
@Slf4j
public class BusinessServiceImpl implements BussinessService {

    @Autowired
    private AccountFeignService accountFeignService;

    @Autowired
    private StorageFeignService storageFeignService;

    @Autowired
    private OrderServiceByTCCImpl orderServiceByTCCImpl;


    @Override
    @GlobalTransactional(name="createOrder",rollbackFor=Exception.class)
    public TOrder saveOrder(OrderVo orderVo) {
        log.info("=============用户下单=================");
        log.info("当前 XID: {}", RootContext.getXID());

        //获取全局唯一订单号  测试使用
        Long orderId = UUIDGenerator.generateUUID();

        //阶段一： 创建订单
        TOrder order = orderServiceByTCCImpl.prepareSaveOrder(orderVo,orderId);

        //扣减库存
        storageFeignService.deduct(orderVo.getProductId(), orderVo.getCount());
        //扣减余额
        accountFeignService.debitByTCC(orderVo.getUserId(), orderVo.getMoney());

        return order;
    }
}
