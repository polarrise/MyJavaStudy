package jinbiao.spring_aop.v2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Configuration用于定义配置类，可替换xml配置文件。 可理解为用spring的时候xml里面的<beans>标签
 * @Bean可理解为用spring的时候xml里面的<bean>标签
 * @ComponentScan 的作用就是根据定义的扫描路径，把符合扫描规则的类装配到spring容器中,basePackageClasses: 用于指定某个类的包的路径进行扫描
 * @EnableAspectJAutoProxy注解，启用了 AOP 功能
 */
@Configuration
@ComponentScan(basePackageClasses = {jinbiao.spring_aop.v2.IBuy.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

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
