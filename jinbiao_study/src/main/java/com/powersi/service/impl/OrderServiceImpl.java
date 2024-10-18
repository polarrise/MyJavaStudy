package com.powersi.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jinbiao.cloud.common.service.RedisService;
import com.jinbiao.cloud.mbg.mapper.OmsOrderMapper;
import com.jinbiao.cloud.mbg.model.OmsOrder;
import com.jinbiao.cloud.mbg.model.OmsOrderExample;
import com.jinbiao.cloud.mbg.model.UmsMember;
import com.jinbiao.cloud.security.service.SecurityMemberService;
import com.powersi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author：Jinbiao
 * @Date：2023/4/17 16:06
 * @Desc：
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private RedissonClient redissonClient;

    @Autowired
    private SecurityMemberService securityMemberService;

    @Autowired
    private OmsOrderMapper omsOrderMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 记录已经下单的用户
     */
    private volatile Map<Long, Boolean> createdOrderMap = new ConcurrentHashMap<>();

    /**
     * 总的下单数量
     */
    private volatile int saleCount = 0;

    public String createOrder(Long userId) {
        try {
            Boolean flag = createdOrderMap.getOrDefault(userId, false);
            //高并发场景下，会造成一个超下单问题
            if (!flag) {
                createdOrderMap.put(userId, true);
                saleCount++;
            }
            return "订单创建成功，总的卖出" + saleCount + "件";
        } catch (Exception e) {
            log.error("createOrder: {}", e.getMessage(), e);
        }
        return "创建订单失败，总的卖出" + saleCount + "件";
    }

    /**
     * 加分布式锁，保证只一个下单成功
     * @param userId
     * @return
     */
    public String createOrderByRedisson(Long userId) {
        UmsMember currentMember = securityMemberService.getCurrentMember();

        //当前下单业务唯一key
        RLock lock = redissonClient.getLock("createOrder:"+currentMember.getId());
        if (lock.tryLock()) {
            try {
                OmsOrderExample omsOrderExample = new OmsOrderExample();
                omsOrderExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(currentMember.getId().toString())).andReceivedAddressEqualTo("createOrder3");
                List<OmsOrder> omsOrders = omsOrderMapper.selectByExample(omsOrderExample);
                if(CollectionUtil.isNotEmpty(omsOrders)){
                    return "您操作过快,请稍微再试";
                }

                // OmsOrder omsOrder = new OmsOrder();
                // omsOrder.setMemberId(currentMember.getId());
                // omsOrder.setReceivedAddress("createOrder2");
                // omsOrder.setReceivedName("rise2");
                // omsOrderMapper.insert(omsOrder);
                return "订单创建成功";
            } catch (Exception e) {
                log.error("createOrder: {}", e.getMessage(), e);
            } finally {
                lock.unlock();
            }
        }
        return "未获取到锁,重复下单";
    }

    public String createOrderByAspect(Long userId) {
        UmsMember currentMember = securityMemberService.getCurrentMember();
        OmsOrderExample omsOrderExample = new OmsOrderExample();
        omsOrderExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(currentMember.getId().toString())).andReceivedAddressEqualTo("createOrder3");
        List<OmsOrder> omsOrders = omsOrderMapper.selectByExample(omsOrderExample);
        if(CollectionUtil.isNotEmpty(omsOrders)){
            return "您操作过快,请稍微再试";
        }
        // OmsOrder omsOrder = new OmsOrder();
        // omsOrder.setMemberId(currentMember.getId());
        // omsOrder.setReceivedAddress("createOrder3");
        // omsOrder.setReceivedName("rise3");
        // omsOrderMapper.insert(omsOrder);
        return "订单创建成功";
    }

    public String createByRedisToken(Long userId) {
        try {
            UmsMember currentMember = securityMemberService.getCurrentMember();
            String hasOrderedKey = "createOder4:"+currentMember.getId();
            if("hasOrdered".equals(redisService.get(hasOrderedKey))){
                return "您操作过快,请稍微再试";
            }
            // OmsOrder omsOrder = new OmsOrder();
            // omsOrder.setMemberId(currentMember.getId());
            // omsOrder.setReceivedAddress("createOrder4");
            // omsOrder.setReceivedName("rise4");
            // omsOrderMapper.insert(omsOrder);
            redisService.set("createOder4:"+currentMember.getId(),"hasOrdered",30000);
            return "订单创建成功";
        } catch (Exception e) {
            log.error("createOrder: {}", e.getMessage(), e);
            return "服务异常";
        }
    }

    @Override
    public String createByDbUniqueIndex(Long userId) {
        UmsMember currentMember = securityMemberService.getCurrentMember();
        try{
            // OmsOrder omsOrder = new OmsOrder();
            // omsOrder.setMemberId(currentMember.getId());
            // omsOrder.setReceivedAddress("createOrder4");
            // omsOrder.setReceivedName("rise4");
            // omsOrder.setProductId(1);
            // omsOrderMapper.insert(omsOrder);
            return "订单创建成功!";
        }catch (DuplicateKeyException e){
            log.info("重复下单违反唯一约束异常:"+e.getMessage());
            return "您操作过快,请稍微再试";
        }
    }

    @Override
    public String updateByDBVersion(Long userId,Integer version) {
        // UmsMember currentMember = securityMemberService.getCurrentMember();
        // Integer memberId = Integer.valueOf(currentMember.getId().toString());
        // OmsOrder omsOrder = new OmsOrder();
        // omsOrder.setMemberId(currentMember.getId());
        // omsOrder.setProductId(1);
        // omsOrder.setReceivedName("rise5");
        // omsOrder.setVersion(version+1);
        // OmsOrderExample omsOrderExample = new OmsOrderExample();
        // omsOrderExample.createCriteria().andMemberIdEqualTo(memberId).andProductIdEqualTo(1).andVersionEqualTo(version);
        // int i = omsOrderMapper.updateByExampleSelective(omsOrder, omsOrderExample);
        // return  i>0 ?"订单更新成功!":"您操作过快,请稍微再试";
        return null;
    }
}
