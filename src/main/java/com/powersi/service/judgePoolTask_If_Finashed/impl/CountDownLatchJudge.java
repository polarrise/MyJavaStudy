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
 * @date 2023/2/3 15:12
 * @desc
 */
@JudgeTaskCode(value = "countDownLatch", name = "使用闭锁countDownLatch去判断线程池任务是否执行成功了")
@Service
@Slf4j
public class CountDownLatchJudge implements JudgeTaskFinishedService {
  @Autowired
  PersonMapper personMapper;

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
    //计数器，判断线程是否执行结束。 该计数器被初始化为一个正数，表示需要等待的事件数量(该demo总干2个异步任务,即2个事件)
    CountDownLatch taskLatch = new CountDownLatch(2);
    FutureTask<CaseCenter> caseInfoFutureTask = new FutureTask<>(new Callable<CaseCenter>() {
      @Override
      public CaseCenter call() throws Exception {
        Thread.sleep(500); //模拟调用耗时
        CaseCenter caseCenter = personMapper.getCaseInfoById(id);
        System.out.println("异步获取案源信息:"+caseCenter);

        //countDown方法递减计数器，表示有一个事件已经发生了
        taskLatch.countDown();
        System.out.println("当前计数器数量：" + taskLatch.getCount());
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
        taskLatch.countDown();
        System.out.println("当前计数器数量：" + taskLatch.getCount());
        return user;
      }
    });
    executorService.submit(userInfoFutureTask);

    //当前线程阻塞，等待计数器置为0
    Thread.sleep(30);
    //await方法等待计数器达到零，即表示需要等待的事情都已经发生(等待所有任务都执行完)
    taskLatch.await();
    System.out.println("异步任务全部执行完毕");
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
