package com.jinbiao.javaStudy.WorkTest;

import com.jinbiao.dynamic_proxy.jdk.UserManagerImpl;
import com.jinbiao.javaStudy.modifier.Parent;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**代理能干嘛？代理可以帮我们增强对象的行为！使用动态代理实质上就是调用时拦截对象方法，对方法进行改造、增强！--其实Spring AOP的底层原理就是动态代理！
 * Cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
 * Cglib动态代理，实现MethodInterceptor接口
 */
public class CglibProxy extends Parent implements MethodInterceptor{

    //需要代理的目标对象
    private Object target;

    //重写拦截方法--
    @Override
    public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        Object invoke = method.invoke(target, arr);
        System.out.println("Cglib动态代理，监听结束！");
        return invoke;
    }

    //定义获取代理对象方法
    public Object getCglibProxy(Object objectTarget){
         //为目标对象target赋值
        this.target=objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类,所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        // 设置回调
        enhancer.setCallback(this);
        //创建并返回代理对象
        return enhancer.create();
    }
    public static void main(String[] args) {
        //实例化CglibProxy对象
        CglibProxy cglib=new CglibProxy();
        //Cglib动态代理实现MethodInterceptor接口,通过对某个目标类修改其字节码生成子类,获取代理对象,然后通过代理对象调用目标类的方法
        UserManagerImpl proxy= (UserManagerImpl) cglib.getCglibProxy(new UserManagerImpl());
        proxy.delUser("cglibAdmin");

        /**
         *  protected: 可以在本包下和其子类访问,把CglibProxy类换到jdk下的包目录就可以了
         */
        // proxy.delUser2("222");
        //  proxy.delUser3("333");

        /** 不在同一包：父类的代理对象是访问不到父类的protected方法的
        Parent parentProxy = (Parent) cglib.getCglibProxy(new Parent());
        parentProxy.test();
        parentProxy.test2();
         */

        // 子类可以访问到父类的protected方法
        CglibProxy pro = new CglibProxy();
        pro.test();
    }
}
