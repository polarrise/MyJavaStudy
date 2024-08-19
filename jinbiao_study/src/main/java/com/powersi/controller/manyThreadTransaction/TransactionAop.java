package com.powersi.controller.manyThreadTransaction;

/**
 * @author WangJinbiao
 * @date 2024/08/19 21：22
 */

import cn.hutool.core.lang.UUID;
import com.powersi.controller.manyThreadTransaction.anno.MainTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * SpringBoot 中的多线程事务处理太繁琐？一个自定义注解直接搞定！
 */
@Aspect
@Component
@Slf4j
public class TransactionAop {

    //用来存储各线程计数器数据(每次执行后会从map中删除)
    private static final Map<String, Object> map = new HashMap<>();

    public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    @Resource
    private PlatformTransactionManager transactionManager;


    /**
     * 1.主事务是必先进aop拦截的。
     *   主事务成功:设置回滚标志:false,无需回滚,等待所有子线程(3个)执行完插入操作
     *   主事务失败:设置回滚标志:true,需要回滚，mainDownLatch计数器归零，清空map里的所有键值对。
     * @param joinPoint
     * @param mainTransaction
     * @throws Throwable
     */
    @Around("@annotation(mainTransaction)")
    public void mainIntercept(ProceedingJoinPoint joinPoint, MainTransaction mainTransaction) throws Throwable {

        String xid = UUID.randomUUID().toString();
        threadLocal.set(xid);

        //初始化计数器
        CountDownLatch mainDownLatch = new CountDownLatch(1);
        CountDownLatch sonDownLatch = new CountDownLatch(mainTransaction.value());//@MainTransaction注解中的参数, 为子线程的数量
        // 用来记录子线程的运行状态，只要有一个失败就变为true
        AtomicBoolean rollBackFlag = new AtomicBoolean(false);
        // 用来存每个子线程的异常，把每个线程的自定义异常向vector的首位置插入，其余异常向末位置插入，避免线程不安全，所以使用vector代替list
        Vector<Throwable> exceptionVector = new Vector<>();

        map.put(xid + "mainDownLatch", mainDownLatch);
        map.put(xid + "sonDownLatch", sonDownLatch);
        map.put(xid + "rollBackFlag", rollBackFlag);
        map.put(xid + "exceptionVector", exceptionVector);

        //开启事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();

        // 设置事务隔离级别,PROPAGATION_REQUIRES_NEW每次都新开启一个事务，且中断当前已经存在的事务。
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            joinPoint.proceed();//执行方法
        } catch (Throwable e) {
            exceptionVector.add(0, e);
            //子线程回滚
            rollBackFlag.set(true);
            //放行所有子线程
            mainDownLatch.countDown();
            log.info("主线程异常:{}，事务回滚啦!，全局事务id:{}",Thread.currentThread().getName(),xid);
        }

        if (!rollBackFlag.get()) {
            // sonDownLatch等待，直到所有子线程执行完插入操作，但此时还没有提交事务
            sonDownLatch.await();

            // 根据rollBackFlag状态放行子线程的await处，告知是回滚还是提交
            mainDownLatch.countDown();

            // 子线程发生了报错，修改了回滚标志位，回滚主事务，
            if(rollBackFlag.get()){
                transactionManager.rollback(status);
                log.info("主线程:{}，事务回滚啦!，全局事务id:{}",Thread.currentThread().getName(),xid);
            }else {
                transactionManager.commit(status);
                log.info("主线程:{}，事务提交啦!，全局事务id:{}", Thread.currentThread().getName(), xid);
            }
        }
        if (CollectionUtils.isNotEmpty(exceptionVector)) {
            map.remove(xid + "mainDownLatch");
            map.remove(xid + "sonDownLatch");
            map.remove(xid + "rollBackFlag");
            map.remove(xid + "exceptionVector");
            throw exceptionVector.get(0);
        }
    }

    /**
     * 拦截子线程
     * 主线程失败了、此时子线程拿到回滚标志位:true，是否执行了db插入操作?减计数器、回滚 : 还没执行db插入操作就直接不执行了，计数器-1.
     * @param joinPoint
     * @throws Throwable
     */
    @Around("@annotation(com.powersi.controller.manyThreadTransaction.anno.SonTransaction)")
    public void sonIntercept(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        String xid = args[args.length - 1].toString();

        CountDownLatch mainDownLatch = (CountDownLatch) map.get(xid + "mainDownLatch");
        if (mainDownLatch == null) {
            //主事务未加注解时, 直接执行子事务
            joinPoint.proceed();
            return;
        }
        CountDownLatch sonDownLatch = (CountDownLatch) map.get(xid + "sonDownLatch");
        AtomicBoolean rollBackFlag = (AtomicBoolean) map.get(xid + "rollBackFlag");
        Vector<Throwable> exceptionVector = (Vector<Throwable>) map.get(xid + "exceptionVector");

        //如果这时有一个子线程已经出错，那当前线程不需要执行
        if (rollBackFlag.get()) {
            sonDownLatch.countDown();
            return;
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();// 开启事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 设置事务隔离级别
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            joinPoint.proceed();//执行方法

            // 当前子线程执行完计数器直接-1，等待其他2个子线程都执行完了之后(计数器归)才放行，初始值为3
            sonDownLatch.countDown();

            // 主线程还没执行完则等待主线程执行完,主线程计数器归零，然后根据整个全局回滚标志位去判断事物该提交还是回滚, 如果mainDownLatch不是0，线程会在此阻塞，直到mainDownLatch变为0
            mainDownLatch.await();

            // 如果能执行到这一步说明所有子线程都已经执行完毕判断如果atomicBoolean是true就回滚false就提交
            if (rollBackFlag.get()) {
                transactionManager.rollback(status);
                log.info("当前线程: {},args参数: {}，事务回滚啦!,全局事务id:{}",Thread.currentThread().getName(),args[0],xid);
            } else {
                transactionManager.commit(status);
                log.info("当前线程: {},args参数: {}，事务提交啦!,全局事务id:{}",Thread.currentThread().getName(),args[0],xid);
            }
        } catch (Throwable e) {
            exceptionVector.add(0, e);

            //异常则回滚当前子线程的事务
            transactionManager.rollback(status);

            //子线程执行失败了，需要告诉主线程真个事务都需要去回滚
            rollBackFlag.set(true);

            sonDownLatch.countDown();

            log.info("当前线程异常: {},args参数:{}，事务回滚啦!,全局事务id:{}", Thread.currentThread().getName(),args[0],xid);
        }
    }
}
