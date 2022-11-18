package com.jinbiao.dynamic_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
代理能干嘛?代理能够增强我们对象的行为! 使用动态代理实质就是在调用对象的方法时做拦截，对方法进行改造，增强
 jdk动态代理本质是利用反射机制,
 */
public class JdkProxy2 implements InvocationHandler {
    private Object target;
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理，监听开始！");
        Object result=method.invoke(target,args);
        System.out.println("JDK动态代理，监听结束！");
        return result;
    }
    /**
     * 定义获取代理对象方法
     */
    private Object getJDKProxy(Object targetObject){
        //为目标对象target赋值
        this.target=targetObject;
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance函数所需参数就可看出
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),targetObject.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) {
        JdkProxy2 jdkProxy2=new JdkProxy2();
        //如果UserManagerImpl没有实现接口则会报异常:com.sun.proxy.$Proxy0 cannot be cast to cn.how2j.springcloud.jdk.UserManagerImpl
        UserManager jdkProxy = (UserManager) jdkProxy2.getJDKProxy(new UserManagerImpl());
        jdkProxy.addUser("com/jinbiao","wang1234");
    }
}
