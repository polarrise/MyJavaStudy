package com.tuling.mall.user.service;

import java.math.BigDecimal;

public interface UserService {
    
    /**
     * 用户账户扣款
     * @param userId
     * @param money 从用户账户中扣除的金额
     */
    void debit(Integer userId, BigDecimal money) ;
}