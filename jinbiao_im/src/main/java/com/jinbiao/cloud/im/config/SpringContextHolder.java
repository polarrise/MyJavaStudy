package com.jinbiao.cloud.im.config;

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

    /**
     * 设置此对象(SpringContextHolder)运行的ApplicationContext(Spring容器)。
     * 通常情况下此调用将被用于初始化对象,在填充普通bean属性之后。
     * 但在初始化回调（如{@link-org.springframework.beans.factory.InitializingBeanafterPropertiesSet（）} or a custom init-method）之前调用
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }
}
