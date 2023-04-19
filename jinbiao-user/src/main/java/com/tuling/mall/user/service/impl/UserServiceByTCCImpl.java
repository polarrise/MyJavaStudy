package com.tuling.mall.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jinbiao.cloud.mbg.mapper.AccountMapper;
import com.jinbiao.cloud.mbg.model.Account;
import com.jinbiao.cloud.mbg.model.AccountExample;
import com.tuling.mall.user.service.UserServiceByTCC;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author：Jinbiao
 * @Date：2023/4/19 17:22
 * @Desc：
 */

@Service
@Slf4j
public class UserServiceByTCCImpl implements UserServiceByTCC {
    @Autowired
    private AccountMapper accountMapper;


    /**
     * 扣减用户金额
     * @param userId
     * @param money
     */
    @Transactional
    @Override
    public boolean debit(Integer userId, BigDecimal money){
        log.info("=============冻结用户账户余额=================");
        log.info("当前 XID: {}", RootContext.getXID());

        checkBalance(userId, money);

        log.info("开始冻结用户 {} 余额", userId);
        //冻结金额
        Integer record = accountMapper.freezeBalance(userId.toString(),money);

        log.info("冻结用户 {} 余额结果:{}", userId, record > 0 ? "操作成功" : "扣减余额失败");
        return true;
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

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        log.info("=============扣减冻结金额=================");

        String userId = actionContext.getActionContext("userId").toString();
        int money = (int) actionContext.getActionContext("money");
        //扣减冻结金额
        accountMapper.reduceFreezeBalance(userId,money);

        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        log.info("=============解冻金额=================");

        String userId = actionContext.getActionContext("userId").toString();
        int money = (int) actionContext.getActionContext("money");
        //解冻金额
        accountMapper.unfreezeBalance(userId,money);

        return true;
    }
}
