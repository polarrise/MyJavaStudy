package com.powersi.spring_aop.v1;

import com.powersi.spring_aop.v2.AppConfig;
import com.powersi.spring_aop.v2.Boy;
import com.powersi.spring_aop.v2.Girl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class); //加载spring配置文件
        Boy boy = context.getBean("boy", Boy.class);
        Girl girl = (Girl) context.getBean("girl");
        boy.buy();
//        girl.buy();
    }
}
