package com.jinbiao.spring_study.springRegisterBeanWays;

import com.jinbiao.spring_study.springRegisterBeanWays.beans.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：Jinbiao
 * @Date：2023/4/11 21:43
 * @Desc：
 * 第2种方式：@Configuration + @Bean方式
 * 这种方式其实也是我们最常用的方式之一，@Configuration用来声明一个配置类，然后使用 @Bean 注解声明一个bean，将其加入到Spring容器中。通常情况下，如果项目中有使用到第三方类库中的工具类的话，我们都是采用这种方式注册Bean。
 */

@Configuration
public class Way2_Configuration_Bean {
    @Bean
    public OrderService orderService1() {
        System.out.println("使用@Configuration+@Bean的方式注册orderService1");
        return new OrderService();
    }
}
