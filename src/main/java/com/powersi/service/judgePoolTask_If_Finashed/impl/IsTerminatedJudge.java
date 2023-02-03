package com.powersi.service.judgePoolTask_If_Finashed.impl;

import com.powersi.annotation.JudgeTaskCode;
import com.powersi.common.service.RedisService;
import com.powersi.dao.PersonMapper;
import com.powersi.entity.CaseCenter;
import com.powersi.entity.User;
import com.powersi.kafka.KafkaProducer;
import com.powersi.service.judgePoolTask_If_Finashed.JudgeTaskFinishedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author wangjinbiao
 * @date 2023/2/3 15:03
 * @desc
 */
@JudgeTaskCode(value = "isTerminated", name = "使用线程池的isTerminated方法去判断线程池任务是否执行成功了")
@Service
@Slf4j
public class IsTerminatedJudge implements JudgeTaskFinishedService {

  @Autowired
  PersonMapper personMapper;

  @Autowired
  KafkaProducer kafkaProducer;

  @Autowired
  RedisService redisService;

  @Override
  public Map judgeTaskFinashed(Long id) throws ExecutionException, InterruptedException{
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    long startTime = System.currentTimeMillis();
    FutureTask<CaseCenter> caseInfoFutureTask = new FutureTask<>(new Callable<CaseCenter>() {
      @Override
      public CaseCenter call() throws Exception {
        Thread.sleep(500); //模拟调用耗时
        CaseCenter caseCenter = personMapper.getCaseInfoById(id);
        System.out.println("异步获取案源信息:"+caseCenter);
        return caseCenter;
      }
    });
    executorService.submit(caseInfoFutureTask);
    Thread.sleep(300); //模拟主线程其它操作耗时
    FutureTask<User> userInfoFutureTask = new FutureTask<>(new Callable<User>() {
      @Override
      public User call() throws Exception {
        Thread.sleep(300); //模拟调用耗时
        User user = personMapper.getUserInfoById(1550400L);
        System.out.println("异步获取用户信息:"+user);
        return user;
      }
    });
    executorService.submit(userInfoFutureTask);
    executorService.shutdown();
    //等待所有任务都结束了继续执行
    while(true){
      //如果关闭后所有任务都已完成，则返回｛@code true｝。请注意，除非首先调用了｛@code shutdown｝或｛@codeshutdownNow｝
      if(executorService.isTerminated()){
        log.info("异步任务已结束！！！");
        break;
      }
      Thread.sleep(80);
    }
    CaseCenter caseInfo = caseInfoFutureTask.get();//获取个人信息结果
    User userInfo = userInfoFutureTask.get();//获取勋章信息结果
    HashMap<String, Object> map = new HashMap<String, Object>() {{
      put("caseInfo", caseInfo);
      put("userInfo", userInfo);
    }};
    System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    return map;
  }
}
