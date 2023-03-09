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

import java.util.HashMap;
import java.util.Map;
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

    private final static Map<String, String> secrets = new HashMap<>();

    static {
        secrets.put("wx06a1cbb1c86a9b04", "15aba5caa36e7f1cb92454f003317471");//业务id,值
    }

    /**
     * 验证签名
     * 验证算法：把timestamp + JsonUtil.object2Json(SortedMap)合成字符串，然后MD5
     */

    public boolean verifySign(SortedMap<String, String> map, RequestHeader requestHeader) {
        String params = requestHeader.getNonceStr() + requestHeader.getTimestamp() + JSON.toJSONString(map) +getSecret(requestHeader.getAppId());
        log.info("客户端签名: {}", requestHeader.getSign());
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
    public String getSecret(String appId) {
        String value = secrets.get(appId);
        if(secrets.get(appId) == null){
            return "当前appId无法进行服务调用,请准确核对您的appId";
        }
        return value;
    }

    @Override
    public User getUserDetailInfo(UserInfoQO userInfoQO) {
        User userInfo = personDao.getUserInfo(userInfoQO);
        return userInfo;
    }
}
