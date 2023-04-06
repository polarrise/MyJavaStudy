package com.tuling.mall.user.demo;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;

import java.util.Properties;

/**
 * 通过核心api手动注册服务到nacos上去。
 */
public class NamingExample {

    public static void main(String[] args) throws NacosException {

        Properties properties = new Properties();
        properties.setProperty("serverAddr", "127.0.0.1:8848");
        //核心接口
        NamingService naming = NamingFactory.createNamingService(properties);
        //服务注册
        naming.registerInstance("jinbiao-order", "11.11.11.11", 8888, "TEST1");

        //服务发现
        System.out.println(naming.getAllInstances("jinbiao-order"));


    }
}