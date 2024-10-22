package com.jinbiao.dynamic_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**代理能干嘛？代理可以帮我们增强对象的行为！使用动态代理实质上就是调用时拦截对象方法，对方法进行改造、增强！--其实Spring AOP的底层原理就是动态代理！
 * JAVA动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
 * JDK动态代理实现InvocationHandler接口
 *
 * 1、如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP
 * 2、如果目标对象实现了接口，可以强制使用CGLIB实现AOP
 * 3、如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换
 */
public class JdkProxy implements InvocationHandler {

    private Object target ;//需要代理的目标对象

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理，监听开始！");
        Object result =method.invoke(target,args);
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
        JdkProxy jdkProxy=new JdkProxy();    //实例化JDKProxy对象
        //如果UserManagerImpl没有实现接口则会报异常:com.sun.proxy.$Proxy0 cannot be cast to cn.how2j.springcloud.jdk.UserManagerImpl
        UserManager proxy= (UserManager) jdkProxy.getJDKProxy(new UserManagerImpl());  //jdk动态代理(实现了InvocationHandler接口),利用反射机制获取某个目标类的代理对象，然后通过代理对象调用目标类的实现方法
        // proxy.addUser("jdkProxyAdmin", "jdkProxy123123");
        proxy.delUser("jdkProxyAdmin");
    }
}
