package com.powersi.component;

import com.powersi.annotation.RequestRepeatIntercept;
import com.powersi.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author：Jinbiao
 * @Date：2023/4/17 17:43
 * @Desc：
 */
@Aspect
@Component
@Slf4j
public class RequestRepeatAspect {
    @Resource
    RedissonClient redissonClient;

    @Around("@annotation(requestRepeatIntercept)")
    public Object intercept(ProceedingJoinPoint joinPoint, RequestRepeatIntercept requestRepeatIntercept) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String api = requestRepeatIntercept.value();
        String userId = args[0].toString();
        String lockKey = api + ":" + userId;
        //当前下单业务唯一key
        RLock lock = redissonClient.getLock(lockKey);
        log.info("lock key:{}", lockKey);
        Object object = null;
        if (lock.tryLock()) {
            try {
                object = joinPoint.proceed();
                log.info("result:{}", object.toString());
                return object;
            } catch (Exception e) {
                log.error("createOrder: {}", e.getMessage(), e);
            } finally {
                //切记：锁的释放要放在finally中，不管执行结果如果，最终都要释放锁。释放锁的时候也需要try，catch，防止锁释放异常（比如：释放已经超时释放的锁），导致最终执行结果不能正常返回。
                lock.unlock();
            }
        }
        return object;
    }
}
