package com.powersi.service.impl;

import com.alibaba.fastjson.JSON;
import com.powersi.dao.PersonDao;
import com.powersi.entity.User;
import com.powersi.model.RequestHeader;
import com.powersi.qo.UserInfoQO;
import com.powersi.service.ThirdPartyCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.SortedMap;

/**
 * @Author：Jinbiao
 * @Date：2023/3/7 15:50
 * @Desc：供第三方服务调用的ServiceImpl
 */
@Service
@Slf4j
public class ThirdPartyCallServiceImpl implements ThirdPartyCallService {

    @Autowired
    PersonDao personDao;

    /**
     * 验证签名
     * 验证算法：把timestamp + JsonUtil.object2Json(SortedMap)合成字符串，然后MD5
     */

    public boolean verifySign(SortedMap<String, String> map, RequestHeader requestHeader) {
        String params = requestHeader.getNonceStr() + requestHeader.getTimestamp() + JSON.toJSONString(map);
        log.debug("客户端签名: {}", requestHeader.getSign());
        if (StringUtils.isEmpty(params)) {
            return false;
        }

        log.info("客户端上传内容: {}", params);

        //与客户端约定一样的加密方法和加密顺序  nonce+timestamp+map参数 的形式
        String paramsSign = DigestUtils.md5DigestAsHex(params.getBytes()).toUpperCase();
        log.info("客户端上传内容加密后的签名结果: {}", paramsSign);

        return requestHeader.getSign().equals(paramsSign);
    }

    @Override
    public User getUserDetailInfo(UserInfoQO userInfoQO) {
        User userInfo = personDao.getUserInfo(userInfoQO);
        return userInfo;
    }
}
