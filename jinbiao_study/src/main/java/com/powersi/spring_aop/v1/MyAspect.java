package com.powersi.spring_aop.v1;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @component （把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）
 * 泛指各种组件，就是说当我们的类不属于各种归类的时候（不属于@Controller、@Services等的时候），我们就可以使用@Component来标注这个类
 * 下面写这个是引入component的扫描组件 <context:component-scan base-package=”com.mmnc”>
 */
@Aspect
@Component
public class MyAspect {

    /**
     * 定义一个环绕通知
     * execution:表示满足某一匹配模式的所有目标类方法连接点~
     */
    @Around("execution(* com.powersi.spring_aop.v1.Impl.*.log2(..))")
    public Object around(ProceedingJoinPoint pjp){
        long start = System.currentTimeMillis();
        Object proceed = null;
        try {
            // 执行目标对象的方法
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(pjp.getSignature().getName() + "  执行耗时：" + (end - start));
        return proceed;
    }

}
