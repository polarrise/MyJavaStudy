package com.jinbiao.proxy.static_proxy;

/**
 * @author WangJinbiao
 * @date 2024/10/27 16：46
 */

/**
 * 我们(打工人)：租房者
 */
public class Renters {

    public static void main(String[] args) {

            System.out.println("我是租客，找到中介..");

            // 创建代理
            LandlordAProxy agency = new LandlordAProxy();

            // 通过代理租房
            agency.rentHouse();
    }
}
