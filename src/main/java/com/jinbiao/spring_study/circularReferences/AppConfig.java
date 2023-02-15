package com.jinbiao.spring_study.circularReferences;
import org.springframework.context.annotation.*;

/**
 * @Configuration用于定义配置类，可替换xml配置文件。 可理解为用spring的时候xml里面的<beans>标签
 * @Bean可理解为用spring的时候xml里面的<bean>标签
 * @ComponentScan 的作用就是根据定义的扫描路径，把符合扫描规则的类装配到spring容器中,basePackageClasses: 用于指定某个类的包的路径进行扫描
 * @EnableAspectJAutoProxy注解，启用了 AOP 功能
 */
@Configuration
@ComponentScan({"com.jinbiao.spring_study.circularReferences"})   //扫描包路径下的所有加了@Controller、Service、@Repository、@Component注解的类
public class AppConfig {

}
