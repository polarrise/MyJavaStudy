package com.powersi.service.judgePoolTask_If_Finashed.impl;

import com.jinbiao.cloud.common.service.RedisService;
import com.powersi.annotation.JudgeTaskCode;
import com.powersi.dao.PersonDao;
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
 * @date 2023/2/3 15:12
 * @desc
 */
@JudgeTaskCode(value = "awaitTermination", name = "使用awaitTermination方法去判断线程池任务是否执行成功了")
@Service
@Slf4j
public class AwaitTerminationJudge implements JudgeTaskFinishedService {
  @Autowired
  PersonDao personDao;

  @Autowired
  KafkaProducer kafkaProducer;

  @Autowired
  RedisService redisService;

  /**
   * 测试结果:如果有一个userInfoFutureTask任务没提交,
   * @param id
   * @return
   * @throws ExecutionException
   * @throws InterruptedException
   */
  @Override
  public Map judgeTaskFinashed(Long id) throws ExecutionException, InterruptedException{
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    long startTime = System.currentTimeMillis();
    FutureTask<CaseCenter> caseInfoFutureTask = new FutureTask<>(new Callable<CaseCenter>() {
      @Override
      public CaseCenter call() throws Exception {
        Thread.sleep(500); //模拟调用耗时
        CaseCenter caseCenter = personDao.getCaseInfoById(id);
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
        User user = personDao.getUserInfoById(1550400L);
        System.out.println("异步获取用户信息:"+user);
        return user;
      }
    });
    executorService.submit(userInfoFutureTask);

    //shutdown()方法必须要在awaitTermination()方法之前调用，awaitTermination方法才会生效。否则会造成死锁。
    executorService.shutdown();
    //当使用awaitTermination时，主线程会处于一种等待的状态，等待线程池中所有的线程都运行完毕后才继续运行。
    executorService.awaitTermination(10,TimeUnit.MINUTES);
    System.out.println("异步任务全部执行完毕");
    CaseCenter caseInfo = caseInfoFutureTask.get();
    User userInfo = userInfoFutureTask.get();
    HashMap<String, Object> map = new HashMap<String, Object>() {{
      put("caseInfo", caseInfo);
      put("userInfo", userInfo);
    }};
    System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    return map;
  }
}
