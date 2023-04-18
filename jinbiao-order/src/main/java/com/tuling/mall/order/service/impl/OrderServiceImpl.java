package com.tuling.mall.order.service.impl;


import com.jinbiao.cloud.common.entity.CommonResult;
import com.jinbiao.cloud.mbg.mapper.TOrderMapper;
import com.jinbiao.cloud.mbg.model.TOrder;
import com.tuling.mall.order.dao.OrderDao;
import com.tuling.mall.order.entity.OrderEntity;
import com.tuling.mall.order.enums.OrderStatusEnum;
import com.tuling.mall.order.rpc.AccountFeignService;
import com.tuling.mall.order.rpc.StorageFeignService;
import com.tuling.mall.order.service.OrderService;
import com.tuling.mall.order.vo.OrderVo;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service("orderService")
@Slf4j
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountFeignService accountFeignService;

    @Autowired
    private StorageFeignService storageFeignService;

    @Autowired
    private TOrderMapper tOrderMapper;

    @Override
    public List<OrderEntity> listByUserId(Integer userId) {
        return orderDao.listByUserId(userId);
    }


    /**
     * 之前分支事务一直注册不上去：要数据源的配置，seata1.5.1是会自动代理数据源的。之前的版本还需要手动代理一下，就是要生成一个DataSourceProxy，定义一个MybatisConfig配置类。手动注册bean：dataSource，sqlSessionFactoryBean
     * 1.在DefaultGlobalTransaction 默认全局事务类的begin方法里面通过TM TransactionManager像TC 也就是单独部署的服务默认是8091端口，获取到一个XID，也就是全局事务id.这个全局事务id伴随着整个下游服务调用。
     * 2.AbstractResourceManager  RM在branchRegister方法里面注册分支事务信息,包含XID全局事务id,得到一个分支分支事务id branch_id。  branch_id(分支事务id),和xid(全局事务id)以及rollback_info回滚信息(包含前置镜像"beforeImage",后置镜像"afterImage"),sqlType":"INSERT","tableName":"t_order"
     * 在第一阶段都会记录在undo_log日志表里面。
     *
     * 调用链路：
     * 第一阶段:业务数据和回滚日志记录在同一个本地事务中提交，释放本地锁和连接资源。
     * 1.在TM端也就是@GlobalTransaction端，当使用分布式事务的时候，会通过TM向TC请求得到一个XID，也就是全局事务id。 seata会在seata的数据库中global_table全局事务表里面记录下全局事务id，以及全局事务相关信息
     * 2.在执行插入订单表的前面，RM会先向TC获取分支事务id, brach_id,然后seata会在branch_table分支事务表里面存入分支事务相关信息
     * 3.再执行我们的插入订单操作的时候，会解析sql，查询前置镜像、后置镜像、并在第一阶段就会生成undo_logo日志信息，包含分支事务id:branch_id、全局事务id:xid、rollback_info：回滚信息，里面包含操作的表、sqlType、前置后置镜像。  新增只有后置镜像：after_image, 更新有前后置镜像
     * 4.释放本地锁和连接资源
     *
     * 第二阶段：
     * 提交异步化，非常快速地完成。
     * 回滚通过一阶段的回滚日志进行反向补偿。
     * @param orderVo
     * @return
     */
    @Override
    @GlobalTransactional(name="createOrder",rollbackFor=Exception.class)
    public TOrder saveOrder(OrderVo orderVo) {
        log.info("=============用户下单=================");
        log.info("当前 XID: {}", RootContext.getXID());

        // 保存订单
        TOrder order = new TOrder();
        order.setUserId(orderVo.getUserId());
        order.setProductId(orderVo.getProductId());
        order.setCount(orderVo.getCount());
        order.setMoney(new BigDecimal(orderVo.getMoney()));
        order.setStatus(OrderStatusEnum.INIT.getValue());

        Integer saveOrderRecord = tOrderMapper.insert(order);
        log.info("保存订单{}", saveOrderRecord > 0 ? "成功" : "失败");

        //扣减库存
        storageFeignService.deduct(orderVo.getProductId(), orderVo.getCount());

        //扣减余额
        accountFeignService.debit(orderVo.getUserId(), orderVo.getMoney());

//        if(!debit){
//            // 解决 feign整合sentinel降级导致Seata失效的处理
//            throw new RuntimeException("账户服务异常降级了");
//        }

        //更新订单
        TOrder order2 = new TOrder();
        order2.setId(order.getId());
        order2.setStatus(OrderStatusEnum.SUCCESS.getValue());
        Integer updateOrderRecord = tOrderMapper.updateByPrimaryKeySelective(order2);
        log.info("更新订单id:{} {}", order.getId(), updateOrderRecord > 0 ? "成功" : "失败");

        return order;
    }


}