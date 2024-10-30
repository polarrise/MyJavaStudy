package com.jinbiao.proxy.static_proxy;

import com.jinbiao.proxy.Rent;

/**
 * @author WangJinbiao
 * @date 2024/10/27 16：38
 */

/**
 * 房东A（真实的出租者）,实现租房接口
 */
public class LandlordA implements Rent {
    @Override
    public void rentHouse() {
        System.out.println("房东A待出租的房子");
    }
}
