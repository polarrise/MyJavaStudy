package com.jinbiao.proxy.dynamic_proxy.jdk;

import com.jinbiao.proxy.static_proxy.LandlordA;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author WangJinbiao
 * @date 2024/10/27 17：11
 */

/**
 * JDK动态代理租房平台
 */
public class JdkDynamicProxyPlatform implements InvocationHandler {

    // 中介有房东信息
    private final LandlordA target = new LandlordA();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("中介带租客看房源信息");

        // 调用房东的租房方法
        Object result = method.invoke(target, args);

        System.out.println("中介带我们跟房东处理租赁合同");

        return result;
    }

    /**
     * 定义获取代理对象方法
     */
    public Object getLandlordProxy(){
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance函数所需参数就可看出
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}
