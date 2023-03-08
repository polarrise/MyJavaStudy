package com.jinbiao.cloud.security.config;

import com.jinbiao.cloud.security.service.SecurityMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * mall-security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends SecurityConfig {

    @Autowired
    private SecurityMemberService securityMemberService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> securityMemberService.loadUserByUsername(username);
    }
}
