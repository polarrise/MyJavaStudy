package com.jinbiao.spring_study.springRegisterBeanWays;

import com.jinbiao.spring_study.springRegisterBeanWays.beans.Student;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author：Jinbiao
 * @Date：2023/4/11 21:47
 * @Desc：
 */
@Configuration
@ComponentScan({"com.jinbiao.spring_study.springRegisterBeanWays"})   //扫描包路径下的所有加了@Controller、Service、@Repository、@Component注解的类
public class AppConfig {
}
