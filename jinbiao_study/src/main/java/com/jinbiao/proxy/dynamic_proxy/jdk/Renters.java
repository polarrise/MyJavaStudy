package com.jinbiao.proxy.dynamic_proxy.jdk;

/**
 * @author WangJinbiao
 * @date 2024/10/27 16：46
 */

import com.jinbiao.proxy.Rent;

/**
 * 我们(打工人)：租房者
 */
public class Renters {

    public static void main(String[] args) {

            System.out.println("我是租客，找到JdkDynamicProxyPlatform中介平台..");

            // 动态生成代理对象
            Rent agency = (Rent)new JdkDynamicProxyPlatform().getLandlordProxy();

            // 通过动态代理租房
            agency.rentHouse();
    }
}
