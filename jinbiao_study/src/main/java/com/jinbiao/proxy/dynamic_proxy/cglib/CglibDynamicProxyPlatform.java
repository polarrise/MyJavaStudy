package com.jinbiao.proxy.dynamic_proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author WangJinbiao
 * @date 2024/10/27 17：35
 */
/**
 * Cglib动态代理租房平台
 */
public class CglibDynamicProxyPlatform implements MethodInterceptor {
    // 创建代理对象的方法
    public Object getLandlordProxy() {
        Enhancer enhancer = new Enhancer();
        // 设置代理类的父类
        enhancer.setSuperclass(LandlordB.class);
        // 设置回调函数
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    /**
     * 拦截方法，加入代理逻辑
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("中介带租客看房源信息");
        // 调用目标类的方法
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("中介带我们跟房东处理租赁合同");
        return result;
    }
}
