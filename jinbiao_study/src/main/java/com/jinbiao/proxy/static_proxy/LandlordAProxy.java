package com.jinbiao.proxy.static_proxy;

import com.jinbiao.proxy.Rent;

/**
 * @author WangJinbiao
 * @date 2024/10/27 16：38
 */

/**
 * 中介A
 */
public class LandlordAProxy implements Rent {

    // 中介有房东信息
    private final LandlordA landlordA = new LandlordA();

    @Override
    public void rentHouse() {
        System.out.println("中介带租客看房源信息");

        // 调用房东的租房方法
        landlordA.rentHouse();

        System.out.println("中介带我们跟房东处理租赁合同");
    }
}
