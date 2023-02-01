package com.powersi.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;

@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private SpringContextHolder() {
    }

    public static <T> T getBean(Class<T> cls) {
        return applicationContext.getBean(cls);
    }

    public static <T> T getBean(String className) {
        return (T) applicationContext.getBean(className);
    }

    public static <T> T getBean(String className, Class<T> cls) {
        return applicationContext.getBean(className, cls);
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }
}
