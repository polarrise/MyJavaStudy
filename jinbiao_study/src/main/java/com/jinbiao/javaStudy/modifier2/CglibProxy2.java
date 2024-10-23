package com.jinbiao.javaStudy.modifier2;

import com.jinbiao.javaStudy.modifier.Target;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**代理能干嘛？代理可以帮我们增强对象的行为！使用动态代理实质上就是调用时拦截对象方法，对方法进行改造、增强！--其实Spring AOP的底层原理就是动态代理！
 * Cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
 * Cglib动态代理，实现MethodInterceptor接口
 */
@Slf4j
public class CglibProxy2 implements MethodInterceptor{

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
        CglibProxy2 cglib=new CglibProxy2();
        /** 在同一包：父类的代理对象可以访问到父类的protected方法的，把CglibProxy类换到其他包目录就访问不了了*/
        Target targetProxy = (Target) cglib.getCglibProxy(new Target());

        // 代理目标对象的private方法,程序报错，提示：java: test5() 在 com.jinbiao.javaStudy.modifier.Target 中是 private 访问控制
        // targetProxy.test1();

        log.info("目标对象的代理对象targetProxy 是否是 目标对象Target的子类?{}",targetProxy instanceof Target);

        // 代理目标对象的protected方法,程序报错，提示：java: test2() 在 com.jinbiao.javaStudy.modifier.Target 中是 protected 访问控制
        // targetProxy.test2();

        // 代理目标对象的default方法(无访问修饰符修饰),程序报错，提示：java: test3()在com.jinbiao.javaStudy.modifier.Target中不是公共的; 无法从外部程序包中对其进行访问
        // targetProxy.test3();

        // 代理目标对象的final方法
        targetProxy.test4();
        // 代理目标对象的static方法
        targetProxy.test5();
        // 代理目标对象的public方法
        targetProxy.test6();

    }
}
