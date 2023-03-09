package com.powersi.utils;

import com.alibaba.fastjson.JSON;
import com.powersi.model.RequestHeader;
import org.springframework.util.DigestUtils;
import java.util.SortedMap;

/**
 * @Author：Jinbiao
 * @Date：2023/3/7 14:50
 * @Desc：
 */
public class GenerateSignUtil {

    public static String generateSign(SortedMap<String, String> map, RequestHeader requestHeader,String secret){

        String params = requestHeader.getNonceStr() + requestHeader.getTimestamp() + JSON.toJSONString(map)+ secret;
        return  DigestUtils.md5DigestAsHex(params.getBytes()).toUpperCase();
    }
}
