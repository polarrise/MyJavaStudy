package com.jinbiao.dynamic_proxy.Cglib;

import com.jinbiao.dynamic_proxy.jdk.UserManagerImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**代理能干嘛？代理可以帮我们增强对象的行为！使用动态代理实质上就是调用时拦截对象方法，对方法进行改造、增强！--其实Spring AOP的底层原理就是动态代理！
 * Cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
 * Cglib动态代理，实现MethodInterceptor接口
 *
 *
 *
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;//需要代理的目标对象
    //重写拦截方法--
    @Override
    public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        Object invoke = method.invoke(target, arr);//方法执行，参数：target 目标对象 arr参数数组
        System.out.println("Cglib动态代理，监听结束！");
        return invoke;
    }

    //定义获取代理对象方法
    public Object getCglibProxy(Object objectTarget){
         //为目标对象target赋值
        this.target=objectTarget;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(objectTarget.getClass()); //设置父类,因为Cglib是针对指定的类生成一个子类,所以需要指定父类
        enhancer.setCallback(this);// 设置回调
        Object result = enhancer.create();//创建并返回代理对象
        return result;
    }
    public static void main(String[] args) {
        CglibProxy cglib=new CglibProxy();  //实例化CglibProxy对象
        UserManagerImpl proxy= (UserManagerImpl) cglib.getCglibProxy(new UserManagerImpl());  //Cglib动态代理实现MethodInterceptor接口,通过对某个目标类修改其字节码生成子类,获取代理对象,然后通过代理对象调用目标类的实现方法
        proxy.delUser("cglibAdmin");
    }
}
