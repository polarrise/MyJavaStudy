package com.tuling.stock.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jinbiao.cloud.mbg.mapper.StockMapper;
import com.jinbiao.cloud.mbg.model.Stock;
import com.jinbiao.cloud.mbg.model.StockExample;
import com.tuling.stock.service.StockService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Fox
 */
@Service
@Slf4j
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;


    /**
     * 当下单之后-扣减库存90->88--扣余额余额不够需要发生回滚，中间发生了脏写的问题的话，如把库存从88改成了86. 会造成二阶段事务不能回滚问题 branchRollback failed.
     * 会造成其他请求过来，下不了单，报错。
     * @param productId 商品编号
     * @param count 扣除数量
     * @return
     */
    @Transactional
    @Override
    public Boolean deduct(Integer productId, int count){
        log.info("=============扣减库存=================");
        log.info("当前 XID: {}", RootContext.getXID());
        // 检查库存
        checkStock(productId,count);

        log.info("开始扣减 {} 库存", productId);

        Integer record = stockMapper.reduceStorage(productId,count);
        log.info("扣减 {} 库存结果:{}", productId, record > 0 ? "操作成功" : "扣减库存失败");
        return record>0 ? Boolean.TRUE:Boolean.FALSE;
    }

    private void checkStock(Integer productId, int count){

        log.info("检查 {} 库存", productId);
        StockExample example = new StockExample();
        example.createCriteria().andProductIdEqualTo(productId);
        List<Stock> stocks = stockMapper.selectByExample(example);

        if(CollectionUtil.isEmpty(stocks)){
            throw new RuntimeException("库存中不存在这样的商品");
        }
        Stock stock = stocks.get(0);
        if (stock.getCount() < count) {
            log.warn("{} 库存不足，当前库存:{}", productId, count);
            throw new RuntimeException("库存不足");
        }

    }

}
