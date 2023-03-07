package com.powersi.test;

import com.google.gson.Gson;
import com.powersi.common.api.CommonResult;
import com.powersi.model.RequestHeader;
import com.powersi.utils.GenerateSignUtil;
import com.powersi.utils.HttpUtil;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author：Jinbiao
 * @Date：2023/3/7 18:13
 * @Desc：模拟客户端/第三方服务调用我的验证加密服务
 */
public class ThirdPartyCallTest {

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= length; i++) {
            int number = random.nextInt(26 + 26 + 10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 如果是个无嵌套的普通的实体类，可使用如下方法：
     * @param body
     * @param clazz
     * @return
     */
    public static Object JSONTOBean(ResponseBody body, Class clazz)
    {
        Object obj = null;
        try
        {
            String json = new String(body.bytes());
            Gson gson = new Gson();
            obj = gson.fromJson(json, clazz);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 2.如果是有嵌套的实体类，可使用如下方法：
     * @param body
     * @param type 注意Type的包为：java.lang.reflect.Type
     * @return
     */
    public static Object JSONTOBean(ResponseBody body, Type type)
    {
        Object obj = null;
        try
        {
            String json = new String(body.bytes());
            Gson gson = new Gson();
            obj = gson.fromJson(json, type);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return obj;
    }


    public static void main(String[] args) {

        SortedMap<String, String> paramMap = new TreeMap<>();
        paramMap.put("phone","17673635094");


        String nonceStr = getRandomString(16);
        Long timestamp = new Date().getTime();
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setNonceStr(nonceStr);
        requestHeader.setTimestamp(timestamp);

        //客户端依据请求参数对象、随机字符串、时间戳。 经过MD5加密/SHA安全散列算法之后生成签名
        String signature = GenerateSignUtil.generateSign(paramMap, requestHeader);

        System.out.println(requestHeader);
        System.out.println(signature);

        //请求方与被请求方 约定要传的参数. 包括:随机字符串、时间戳、md5加密后的签名:
        Map<String, String> headers = new HashMap<>(3);
        headers.put("nonceStr",nonceStr);
        headers.put("signature",signature);
        headers.put("timestamp", timestamp.toString());
        ResponseBody responseBody = HttpUtil.doPostJson("http://localhost:8011/jinbiao-cool/thirdPartyService/getUserDetailInfo", paramMap, headers);

        CommonResult commonResult = (CommonResult) JSONTOBean(responseBody, CommonResult.class);
        System.out.println("客户端/第三方服务调用我的验证加密服务结果:"+commonResult);
        System.out.println(commonResult.getData());

    }
}
