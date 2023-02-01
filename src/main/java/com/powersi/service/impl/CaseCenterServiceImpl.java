package com.powersi.service.impl;

import com.powersi.common.service.RedisService;
import com.powersi.dao.PersonMapper;
import com.powersi.entity.CaseCenter;
import com.powersi.entity.User;
import com.powersi.kafka.KafkaProducer;
import com.powersi.service.CaseCenterService;
import com.powersi.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author com.jinbiao
 * @date 2021/11/24
 * @apiNote
 */
@Service
public class CaseCenterServiceImpl implements CaseCenterService {
  @Autowired
  PersonMapper personMapper;

  @Autowired
  KafkaProducer kafkaProducer;

  @Autowired
  RedisService redisService;

  @Override
  public List<CaseCenter> getAllCase(Map map) {
      return personMapper.getAllCase(map);
  }

  @Override
  public Map getCaseInfoById(Long id) throws ExecutionException, InterruptedException {

    Thread.sleep(300); //模拟主线程其它操作耗时
    CompletableFuture<CaseCenter> caseCenterFuture = CompletableFuture.supplyAsync(
        ()->{
          CaseCenter caseCenter = personMapper.getCaseInfoById(id);
          System.out.println("原始CompletableFuture方法任务");
          return caseCenter;
        }
    );

    CompletableFuture thenAcceptFuture = caseCenterFuture.thenAccept((a) -> {
      User user = personMapper.getUserInfoById(a.getUserId());
      System.out.println("没有返回结果:"+user);
    });

    CompletableFuture<User> thenApplyFuture  = caseCenterFuture.thenApply((a) -> {
      User user = personMapper.getUserInfoById(a.getUserId());
      System.out.println("有返回结果:"+user);
      return user;
    });

    /**
     * thenCombine / thenAcceptBoth / runAfterBoth都表示：将两个CompletableFuture组合起来，只有这两个都正常执行完了，才会执行某个任务。
     * 区别在于：
     * thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值
     * thenAcceptBoth: 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值
     * runAfterBoth 不会把执行结果当做方法入参，且没有返回值。
     */
    ExecutorService executor = Executors.newFixedThreadPool(2);
    CompletableFuture<Map> combineFuture = caseCenterFuture.thenCombineAsync(thenApplyFuture, (s, w) -> {
      Map map = new HashMap();
      map.put("caseInfo",s);
      map.put("userInfo",w);
      return map;
    }, executor);

    System.out.println("thenAcceptFuture.get():"+thenAcceptFuture.get());
    System.out.println("thenApplyFuture.get():"+thenApplyFuture.get());
    return combineFuture.get() ;
  }

  /**
   * 如果我们不使用Future进行并行异步调用，而是在主线程串行进行的话，耗时大约为300+500+300 = 1100 ms。
   * 可以发现，future+线程池异步配合，提高了程序的执行效率。
   * 但是Future对于结果的获取，不是很友好，只能通过阻塞或者轮询的方式得到任务的结果。
   */
  @Override
  public Map testFuture(Long id) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    long startTime = System.currentTimeMillis();
    FutureTask<CaseCenter> caseInfoFutureTask = new FutureTask<>(new Callable<CaseCenter>() {
      @Override
      public CaseCenter call() throws Exception {
        Thread.sleep(500); //模拟调用耗时
        CaseCenter caseCenter = personMapper.getCaseInfoById(id);
        System.out.println("异步获取案源信息:");
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
        return user;
      }
    });
    executorService.submit(userInfoFutureTask);
    CaseCenter caseInfo = caseInfoFutureTask.get();//获取个人信息结果
    User userInfo = userInfoFutureTask.get();//获取勋章信息结果
    HashMap<String, Object> map = new HashMap<String, Object>() {{
      put("caseInfo", caseInfo);
      put("userInfo", userInfo);
    }};
    System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    return map;
  }

  /**
  * CompletableFuture使用场景:1.创建异步任务   2.简单任务异步回调   3.多个任务组合处理
  */
  @Override
  public Map testCompletableFuture(Long id) throws InterruptedException, ExecutionException {
    long startTime = System.currentTimeMillis();
    /**
     * 1.创建异步任务: CompletableFuture创建异步任务，一般有supplyAsync(支持返回值)和runAsync(没有返回值)两个方法
     */
    CompletableFuture<CaseCenter>caseCenterFuture = CompletableFuture.supplyAsync(
        ()->{
          try {
            Thread.sleep(500); //模拟调用耗时
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          CaseCenter caseCenter = personMapper.getCaseInfoById(id);
          System.out.println("查询案源信息异步任务:"+caseCenter);
          return caseCenter;
        }
    );
    Thread.sleep(400); //模拟主线程其它操作耗时

    /**
     * CompletableFuture的thenApply方法表示，第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果，作为入参，
     * 传递到回调方法中，并且回调方法是有返回值的。
     */
    CompletableFuture<User> userFuture  = caseCenterFuture.thenApply((a) -> {
      try {
        Thread.sleep(300); //模拟调用耗时
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      User user = personMapper.getUserInfoById(a.getUserId());
      System.out.println("查询用户信息异步任务(有返回结果):"+user);

      redisService.set("user",user);

      kafkaProducer.sendMsg(GsonUtils.toJson(user));
      return user;
    });
    HashMap<String, Object> map = new HashMap<String, Object>() {{
      put("caseInfo", caseCenterFuture.get());
      put("userInfo", userFuture.get());
    }};
    System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    return map;
  }
}
