package com.powersi.controller.manyThreadTransaction;

import com.powersi.controller.manyThreadTransaction.anno.SonTransaction;
import com.powersi.dao.TestDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author WangJinbiao
 * @date 2024/08/19 21：17
 */
@Service
@Slf4j
public class SonService {

    @Resource
    private TestDao testDao;

    @Transactional(rollbackFor = Exception.class)
    @Async("commonThreadPoolExecutor")
    @SonTransaction
    public void sonMethod1(String args, String xid) {
        testDao.insertTest1();
        log.info("当前线程:{}，{}:开启了线程,全局事务id:{}", Thread.currentThread().getName(), args, xid);
    }

    @Transactional(rollbackFor = Exception.class)
    @Async("commonThreadPoolExecutor")
    @SonTransaction
    public void sonMethod2(String args, String xid) {
        testDao.insertTest2();
        log.info("当前线程:{}，{}:开启了线程,全局事务id:{}", Thread.currentThread().getName(), args, xid);

    }


    @Transactional(rollbackFor = Exception.class)
    @Async("commonThreadPoolExecutor")
    @SonTransaction
    public void sonMethod3 (String args, String xid){
        testDao.insertTest3();
        log.info("当前线程:{}，{}:开启了线程,全局事务id:{}", Thread.currentThread().getName(), args, xid);
        throw new RuntimeException(args+"执行出错了");
    }

    @Transactional(rollbackFor = Exception.class)
    public void sonMethod4 (String args){
        testDao.insertTest();
        log.info("主线程:{}，{}:没有开启新的线程", Thread.currentThread().getName(), args);
        throw new RuntimeException(args+"执行出错了");
    }
    }
