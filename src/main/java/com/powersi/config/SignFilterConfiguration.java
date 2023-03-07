package com.powersi.config;

import com.powersi.filter.SignFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * 签名的过滤器配置bean，在特定请求的url进来之前，添加默认参数signMaxTime：签名的最大有效时间
 */
@Configuration
public class SignFilterConfiguration {

    @Value("${sign.maxTime}")
    private String signMaxTime;

    //filter中的初始化参数
    private Map<String, String> initParametersMap = new HashMap<>();

    @Bean
    public FilterRegistrationBean contextFilterRegistrationBean() {
        initParametersMap.put("signMaxTime", signMaxTime);
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(signFilter());
        registration.setInitParameters(initParametersMap);
        registration.addUrlPatterns("/thirdPartyService/*");
        registration.setName("SignFilter");
        // 设置过滤器被调用的顺序
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public Filter signFilter() {
        return new SignFilter();
    }
}