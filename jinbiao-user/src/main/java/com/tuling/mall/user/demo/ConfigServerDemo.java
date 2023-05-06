package com.tuling.mall.user.demo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;


/**
 * @Author：Jinbiao
 * @Date：2023/5/6 13:12
 * @Desc：
 */
public class ConfigServerDemo {

    public static void main(String[] args) throws NacosException, InterruptedException {
        String serverAddr = "localhost";
        String dataId = "nacos-config-demo.yaml";
        String group = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        //获取配置服务
        ConfigService configService = NacosFactory.createConfigService(properties);
        //从配置中心拉取配置
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
        //注册监听器
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("感知配置变化:" + configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });


        //发布配置
        //boolean isPublishOk = configService.publishConfig(dataId, group, "content");
        //System.out.println(isPublishOk);
        //发送properties格式
        configService.publishConfig(dataId,group,"common.age=31", ConfigType.PROPERTIES.getType());

        Thread.sleep(30000);
        content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);

//        boolean isRemoveOk = configService.removeConfig(dataId, group);
//        System.out.println(isRemoveOk);
//        Thread.sleep(3000);

//        content = configService.getConfig(dataId, group, 5000);
//        System.out.println(content);
//        Thread.sleep(300000);

    }
}
