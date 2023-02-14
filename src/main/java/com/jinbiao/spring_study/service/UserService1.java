package com.jinbiao.spring_study.service;

import com.jinbiao.spring_study.dao.UserDao;
import com.jinbiao.spring_study.dao.UserMapper;
import com.powersi.dao.PersonMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author wangjinbiao
 * @date 2023/2/14 14:30
 * @desc Bean的生命周期:
 * UserService->无参构造方法->普通对象->依赖注入->初始化前(@PostConstruct)->
 * 初始化(InitializingBean)->初始化后(AOP)->代理对象->放入单例池Map->Bean
 */
@Component
public class UserService1 implements InitializingBean {

    @Autowired
    private OrderService orderService;

    @Autowired
    UserDao userDao;

    //@Autowired
    //private UserMapper userMapper;

    private User admin;

    //Spring创建Bean的初始化前方法。Spring启动时候会去调用这里注解里面的方法
    @PostConstruct
    public void setAdminUser() {
        System.out.println("初始化前方法执行==");
        //mysql->User->this.admin赋值
        List<User> users = userDao.findAll();
        //  userMapper.getCaseInfoById(11L);
        this.admin = users.get(0);
    }

    //Spring在创建Bean的时候，在属性设置完了之后，如果这个Bean实现了InitializingBean接口，会调用这个方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化方法执行==");
    }

    public void test() {
        System.out.println("test方法执行==");
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}