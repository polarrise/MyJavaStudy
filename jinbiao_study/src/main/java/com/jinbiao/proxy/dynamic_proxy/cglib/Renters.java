package com.jinbiao.proxy.dynamic_proxy.cglib;

/**
 * @author WangJinbiao
 * @date 2024/10/27 16：46
 */

/**
 * 我们(打工人)：租房者
 */
public class Renters {
    public static void main(String[] args) {
            System.out.println("我是租客，找到CglibDynamicProxyPlatform中介平台..");
            // 动态生成代理对象
            LandlordB landlordBAgency = (LandlordB) new CglibDynamicProxyPlatform().getLandlordProxy();
            // 通过动态代理租房
            landlordBAgency.rentHouse();
    }
}
