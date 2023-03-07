package com.powersi.service;

import com.powersi.entity.User;
import com.powersi.model.RequestHeader;
import com.powersi.qo.UserInfoQO;

import java.util.SortedMap;

/**
 * @Author：Jinbiao
 * @Date：2023/3/7 15:48
 * @Desc：供第三方服务调用的Service
 */
public interface ThirdPartyCallService {


    boolean verifySign(SortedMap<String, String> map, RequestHeader requestHeader);

    /**
     * 获取用户信息
     * @return
     */
    User getUserDetailInfo(UserInfoQO userInfoQO);
}
