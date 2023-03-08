package com;

import com.jinbiao.cloud.mbg.mapper.TestMapper;
import com.jinbiao.cloud.mbg.mapper.UmsMemberMapper;
import com.jinbiao.cloud.mbg.model.Test;
import com.jinbiao.cloud.mbg.model.UmsMember;
import com.jinbiao.cloud.security.domain.MemberDetails;
import com.jinbiao.cloud.security.util.JwtTokenUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UserDetails;

@MapperScan({"com.powersi.dao","com.jinbiao.cloud.mbg.*"})
@SpringBootApplication(scanBasePackages = {"com.powersi"})
@ComponentScan(basePackages = {"com.powersi.*", "com.config","com.jinbiao.cloud.*"})   //@ComponentScan 用于指定 Spring 在初始化容器时要扫描的包。basePackages 属性用于指定要扫描的包。
@EnableAspectJAutoProxy  //添加@EnableAspectJAutoProxy注解来放开代理的使用
public class SpringBootDemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoApplication.class, args);
        TestMapper testMapper = (TestMapper) context.getBean("testMapper");
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);

        UmsMemberMapper umsMemberMapper = (UmsMemberMapper) context.getBean("umsMemberMapper");

        UmsMember umsMember = umsMemberMapper.selectByPrimaryKey(393429L);
        System.out.println(umsMember);

        JwtTokenUtil jwtTokenUtil = (JwtTokenUtil) context.getBean("jwtTokenUtil");
        UserDetails userDetails = new MemberDetails(umsMember);
        String tokens = jwtTokenUtil.generateToken(userDetails);
        System.out.println(tokens);

    }

}
