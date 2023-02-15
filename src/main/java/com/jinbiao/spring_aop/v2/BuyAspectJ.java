package com.jinbiao.spring_aop.v2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 注解 @Component 表明它将作为一个Spring Bean 被装配
 * 注解 @Aspect 表示它是一个切面。在调用目标对象的方法(切点)时，代理对象首先会调用这些通知，然后再调用目标对象的方法
 */
@Aspect
@Component
public class BuyAspectJ {
    /**
     * @Before 这个注解，表示他将在buy方法执行之前执行
     * execution:声明了切点,表示满足某一匹配模式的所有目标类方法连接点~
     */
    @Before("execution(* com.jinbiao.spring_aop.v2.IBuy.buy(..))")
    public void before(){
        System.out.println("男孩女孩都买自己喜欢的东西");
    }
    @After("execution(* com.jinbiao.spring_aop.v2.IBuy.buy(..))")
    public void after() {
        System.out.println("After ...");
    }
    @AfterReturning("execution(* com.jinbiao.spring_aop.v2.IBuy.buy(..))")
    public void AfterReturning() {
        System.out.println("AfterReturning ...");
    }
    @Around("execution(* com.jinbiao.spring_aop.v2.IBuy.buy(..))")
    public void Around(ProceedingJoinPoint pj) {
        try {
            System.out.println("Around aaa ...");
            pj.proceed();   //如果不调用该对象的 proceed() 方法，表示原目标方法被阻塞调用
            System.out.println("Around bbb ...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
