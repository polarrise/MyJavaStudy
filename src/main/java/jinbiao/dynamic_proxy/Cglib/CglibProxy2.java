package jinbiao.dynamic_proxy.Cglib;


import com.jinbiao.dynamic_proxy.jdk.UserManager;
import com.jinbiao.dynamic_proxy.jdk.UserManagerImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 /**代理能干嘛？代理可以帮我们增强对象的行为！使用动态代理实质上就是调用时拦截对象方法，对方法进行改造、增强！--其实Spring AOP的底层原理就是动态代理！
 * Cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
 * Cglib动态代理，实现MethodInterceptor接口
 */

public class CglibProxy2 implements MethodInterceptor {
    private Object target;
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        method.invoke(target,objects);
        System.out.println("Cglib动态代理，监听结束！");
        return null;
    }

    public Object getCglibProxy(Object objectTarget){
        this.target=objectTarget;   //就是UserManagerImpl对象
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(objectTarget.getClass()); //设置父类,因为Cglib是针对指定的类生成一个子类,所以需要指定父类
        enhancer.setCallback(this);// 设置回调
        Object result = enhancer.create();//创建并返回代理对象
        return result;
    }

    public static void main(String[] args) {
        CglibProxy2 cglibProxy2=new CglibProxy2();
        UserManager cglibProxy = (UserManager)cglibProxy2.getCglibProxy(new UserManagerImpl());
        cglibProxy.addUser("jinbiao","wang1234");
    }

}
