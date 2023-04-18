package com.tuling.mall.user.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.jinbiao.cloud.mbg.mapper.seata_at_account.AccountMapper;
import com.jinbiao.cloud.mbg.model.seata_at_account.Account;
import com.jinbiao.cloud.mbg.model.seata_at_account.AccountExample;
import com.tuling.mall.user.service.UserService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author Fox
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    
    private static final String ERROR_USER_ID = "1002";
    
    @Autowired
    private AccountMapper accountMapper;
    
    /**
     * 扣减用户金额
     * @param userId
     * @param money
     */
    @Transactional
    @Override
    public void debit(Integer userId, BigDecimal money){
        log.info("=============用户账户扣款=================");
        log.info("当前 XID: {}", RootContext.getXID());
    
        checkBalance(userId, money);
        
        log.info("开始扣减用户 {} 余额", userId);
        Integer record = accountMapper.reduceBalance(userId,money);
        
//        if (ERROR_USER_ID.equals(userId)) {
//            // 模拟异常
//            throw new RuntimeException("account branch exception");
//        }
        log.info("扣减用户 {} 余额结果:{}", userId, record > 0 ? "操作成功" : "扣减余额失败");
    }
    
    private void checkBalance(Integer userId, BigDecimal money){
        log.info("检查用户 {} 余额", userId);
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andUserIdEqualTo(userId);
        List<Account> accounts = accountMapper.selectByExample(accountExample);

        if(CollectionUtil.isEmpty(accounts)){
            throw new RuntimeException("不存在当前账户:"+userId);
        }
        Account account = accounts.get(0);

        if (account.getMoney().compareTo(money)< 0) {
            log.warn("用户 {} 余额不足，当前余额:{}", userId, account.getMoney());
            throw new RuntimeException("余额不足");
        }
        
    }
}
