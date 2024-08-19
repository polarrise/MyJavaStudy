package com.powersi.controller.manyThreadTransaction;

import com.powersi.controller.manyThreadTransaction.anno.MainTransaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
/**
 * @author WangJinbiao
 * @date 2024/08/19 21：14
 */
@Service
public class MainService {

    @Resource
    private SonService sonService;

    @MainTransaction(3) //调用的方法中sonMethod1/sonMethod2/sonMethod3使用@Async开启了线程, 所以参数为: 3
    @Transactional(rollbackFor = Exception.class)
    public void test1() {

        // 设置全局事务id
        String xid = TransactionAop.threadLocal.get();
        sonService.sonMethod1("张三",xid);
        sonService.sonMethod2( "李四",xid);
        sonService.sonMethod3("王五",xid);
        sonService.sonMethod4( "rise");
    }
}
