package com.jinbiao.spring_study;

import com.alibaba.druid.pool.DruidDataSource;
import com.jinbiao.spring_aop.v2.IBuy;
import com.jinbiao.spring_study.service.JDBCConfig;
import com.jinbiao.spring_study.service.OrderService;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Configuration用于定义配置类，可替换xml配置文件。 可理解为用spring的时候xml里面的<beans>标签
 * @Bean可理解为用spring的时候xml里面的<bean>标签
 * @ComponentScan 的作用就是根据定义的扫描路径，把符合扫描规则的类装配到spring容器中,basePackageClasses: 用于指定某个类的包的路径进行扫描
 * @EnableAspectJAutoProxy注解，启用了 AOP 功能
 */
@Configuration
@ComponentScan({"com.jinbiao.spring_study"})   //扫描包路径下的所有加了@Controller、Service、@Repository、@Component注解的类
@MapperScan("com.jinbiao.spring_study.dao")   //MyBatis扫描dao接口
@EnableAspectJAutoProxy(proxyTargetClass = true)  //开启切面
@PropertySource("classpath:jdbc.properties")     //读取jdbc数据源文件
@Import({JDBCConfig.class})                     //导入配置类
@EnableTransactionManagement                   //开启事务管理器
public class AppConfig {

  //疑问已经在OrderService上加了@Component注解了
  //@Bean
  //public OrderService orderService(){
  //  System.out.println("手动注入Bean到Spring容器中:如果OrderService上加了Component注解了则不会执行了，否则这里会执行==");
  //  return new OrderService();
  //}


  @Bean
  public OrderService orderService1(){
    return new OrderService();
  }

  @Bean
  public OrderService orderService2(){
    return new OrderService();
  }

}


/**在spring的配置文件spring.xml中这样配置，也可达到同样的效果！！！  1.把目标对象注册到spring 2.把切面类注册到spring 3.声明切面类,定义切点,通知。
             --目标对象--
 8     <bean id="boy" class="cn.how2j.springcloud.springAop_ibuy.Impl.Boy"/>
 9           --切面bean--
 10     <bean id="buyAspectJ" class="cn.how2j.springcloud.springAop_ibuy.Impl.BuyAspectJ"/>     向spring注册这个切面bean
 11          --面向切面编程--
 12     <aop:config>
 13         <aop:aspect ref="buyAspectJ">   表示它是一个切面
 14             -- 定义切点 --
 15             <aop:pointcut expression="execution(cn.how2j.springcloud.springAop_ibuy.Impl.Boy.buy(..))" id="embark"/>
 16            -- 声明前置通知 (在切点方法被执行前调用)--
 17             <aop:before method="before" pointcut-ref="embark"/>
 18            -- 声明后置通知 (在切点方法被执行后调用)--
 19             <aop:after method="after" pointcut-ref="embark"/>
 20         </aop:aspect>
 21     </aop:config>
 */
