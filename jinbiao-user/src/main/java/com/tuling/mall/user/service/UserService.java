package com.tuling.mall.user.service;

import com.tuling.mall.user.entity.UserEntity;

import java.math.BigDecimal;

public interface UserService {
    
    /**
     * 用户账户扣款
     * @param userId
     * @param money 从用户账户中扣除的金额
     */
    void debit(Integer userId, BigDecimal money) ;

    /**
     * sentinel测试
     * @param id
     * @return
     */
    UserEntity getById(Integer id);
}