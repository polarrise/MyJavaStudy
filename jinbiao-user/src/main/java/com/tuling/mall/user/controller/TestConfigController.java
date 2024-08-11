package com.tuling.mall.user.controller;

import com.jinbiao.cloud.common.entity.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：Jinbiao
 * @Date：2023/5/6 14:34
 * @Desc：测试配置中心：--@Value注解可以获取到配置中心的值，但是无法动态感知修改后的值，需要利用@RefreshScope注解
 */
@RestController
@RefreshScope    //动态感知修改后的值，注入内存中最新的值
public class TestConfigController implements ApplicationListener<RefreshScopeRefreshedEvent> {

    @Value("${common.name}")
    private String name;

    @Value("${common.age}")
    private String age;

    @GetMapping("/common")
    public CommonResult hello() {
        return CommonResult.success(name+age);
    }

    //触发@RefreshScope执行逻辑会导致@Scheduled定时任务失效。 定时任务是在bean的后置处理器中处理的。
    @Scheduled(cron = "*/3 * * * * ?")  //定时任务每隔3s执行一次
    public void execute() {
        System.out.println("定时任务正常执行。。。。。。");
    }

    /**
     * 解决动态感知配置文件值后 定时任务失效问题。 只要配置更新就会触发这个事件。调这个方法，就会去重新创建这个bean，定时任务就又生效了
     * @param event
     */
    @Override
    public void onApplicationEvent(RefreshScopeRefreshedEvent event) {
        this.execute();
    }
}
