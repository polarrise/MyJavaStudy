package com.jinbiao.spring_study.service;

import com.jinbiao.spring_study.dao.UserDao;
import com.jinbiao.spring_study.dao.UserMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @author wangjinbiao
 * @date 2023/2/14 14:30
 * @desc Bean的生命周期:
 * UserService->无参构造方法->普通对象->依赖注入->初始化前(@PostConstruct)->
 * 初始化(InitializingBean)->初始化后(AOP)->代理对象->放入单例池Map->Bean
 * ps：spring bean在初始化和销毁的时候我们可以触发一些自定义的回调操作。
 *
  UserServiceInitProcess 创建生命周期：
   1.推断构造方法，然后创建一个UserServiceInitProcess普通对象
   2.依赖主语填充加了AutoWired注解的属性
   4.初始化前方法：Spring创建Bean的时候会去执行加了@PostConstruct注解的初始化前方法
   5.自定义的初始化方法：实现InitializingBean接口重写afterPropertiesSet方法
   6.初始化后方法会进行AOP。  比如这个对象里面有方法被设置成了切点。 或者有方法上加了事务注解，那么就会进行AOP。生成代理对象
   7.放入单例池    (没发生过AOP? 普通对象：代理对象)
   8.在销毁bean之前会去执行加了@PreDestroy注解的销毁前方法
   9.容器关闭，则销毁bean.
   10.容器关闭后会调用DisposableBean里面的destory方法。
 */
@Component
public class UserServiceInitProcess implements InitializingBean, DisposableBean, BeanPostProcessor,BeanNameAware, ApplicationContextAware {

    @Autowired
    UserDao userDao;

    //@Autowired
    //private UserMapper userMapper;

    private User admin;

    private ApplicationContext applicationContext;

    //1.回调一些列Aware接口
    @Override
    public void setBeanName(String s) {
        System.out.println("回调BeanNameAware的方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Object userServiceInitProcess = applicationContext.getBean("userServiceInitProcess");
        this.applicationContext = applicationContext;
        System.out.println("回调ApplicationContextAware的方法:"+applicationContext);
    }


    //Spring创建Bean的初始化前方法。Spring启动时候会去调用这里注解里面的方法
    @PostConstruct
    public void setAdminUser() {
        System.out.println("初始化前方法执行==");
        //mysql->User->this.admin赋值
        List<User> users = userDao.findAll();
        //  userMapper.getCaseInfoById(11L);
        this.admin = users.get(0);
    }

    //Spring在创建Bean的时候，在属性设置完了之后，如果这个Bean实现了InitializingBean接口，会调用这个方法init-method
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化方法执行==");
    }


    public void test() {
        System.out.println("test方法执行==");
    }

    @PreDestroy
    public void preDestory() {
        System.out.println("===Bean销毁前执行方法===");
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("===Bean销毁的时候执行===");
    }

}
