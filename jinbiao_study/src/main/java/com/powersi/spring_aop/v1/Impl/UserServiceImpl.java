package com.powersi.spring_aop.v1.Impl;

import com.powersi.spring_aop.v1.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public void log1() {
        long start = System.currentTimeMillis();  //毫秒数
        try {
            Thread.sleep(5);
            System.out.println("log1 方法执行了 ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();    //毫秒数
        System.out.println("log1方法执行耗时：" + (end - start));
    }

    @Override
    public void log2() {
        try {
            Thread.sleep(5);
            System.out.println("log2 方法执行了 ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
