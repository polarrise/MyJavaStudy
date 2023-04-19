//package com.tuling.stock.service.impl;
//
//import com.jinbiao.cloud.mbg.mapper.StockMapper;
//import com.jinbiao.cloud.mbg.model.Stock;
//import com.tuling.stock.service.StockServiceByTCC;
//import io.seata.core.context.RootContext;
//import io.seata.rm.tcc.api.BusinessActionContext;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * @Author：Jinbiao
// * @Date：2023/4/19 17:09
// * @Desc：
// */
//@Service
//@Slf4j
//public class StockServiceByTCCImpl implements StockServiceByTCC {
//
//    @Autowired
//    private StockMapper stockMapper;
//
//    @Transactional
//    @Override
//    public boolean deduct(String productId, int count){
//        log.info("=============冻结库存=================");
//        log.info("当前 XID: {}", RootContext.getXID());
//
//        // 检查库存
//        checkStock(productId,count);
//
//        log.info("开始冻结 {} 库存", productId);
//        //冻结库存
//        Integer record = stockMapper.freezeStorage(productId,count);
//        log.info("冻结 {} 库存结果:{}", productId, record > 0 ? "操作成功" : "扣减库存失败");
//        return true;
//    }
//
//    private void checkStock(String productId, int count){
//
//        log.info("检查 {} 库存", productId);
//        Stock stock = stockMapper.findByProductId(productId);
//        if (stock.getCount() < count) {
//            log.warn("{} 库存不足，当前库存:{}", productId, count);
//            throw new RuntimeException("库存不足");
//        }
//
//    }
//
//
//    @Override
//    public boolean commit(BusinessActionContext actionContext) {
//        log.info("=============扣减冻结库存=================");
//
//        String commodityCode = actionContext.getActionContext("commodityCode").toString();
//        int count = (int) actionContext.getActionContext("count");
//        //扣减冻结库存
//        stockMapper.reduceFreezeStorage(commodityCode,count);
//
//        return true;
//    }
//
//    @Override
//    public boolean rollback(BusinessActionContext actionContext) {
//        log.info("=============解冻库存=================");
//
//        String commodityCode = actionContext.getActionContext("commodityCode").toString();
//        int count = (int) actionContext.getActionContext("count");
//        //扣减冻结库存
//        stockMapper.unfreezeStorage(commodityCode,count);
//
//        return true;
//    }
//}
