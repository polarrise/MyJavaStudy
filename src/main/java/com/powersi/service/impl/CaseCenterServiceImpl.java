package com.powersi.service.impl;

import com.powersi.dao.PersonMapper;
import com.powersi.entity.CaseCenter;
import com.powersi.entity.User;
import com.powersi.service.CaseCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    @Override
    public List<CaseCenter> getAllCase(Map map) {
        return personMapper.getAllCase(map);
    }

  @Override
  public Map getCaseInfoById(Long id) throws ExecutionException, InterruptedException {

    Thread.sleep(300); //模拟主线程其它操作耗时

    CompletableFuture<CaseCenter> orgFuture = CompletableFuture.supplyAsync(
        ()->{
          CaseCenter caseCenter = personMapper.getCaseInfoById(id);
          System.out.println("原始CompletableFuture方法任务");
          return caseCenter;
        }
    );

    CompletableFuture thenAcceptFuture = orgFuture.thenAccept((a) -> {
      User user = personMapper.getUserInfoById(a.getUserId());
      System.out.println("没有返回结果:"+user);
    });

    CompletableFuture<User> thenApplyFuture  = orgFuture.thenApply((a) -> {
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
    CompletableFuture<Map> stringCompletableFuture = orgFuture.thenCombineAsync(thenApplyFuture, (s, w) -> {
      Map map = new HashMap();
      map.put("caseInfo",s);
      map.put("userInfo",w);
      return map;
    }, executor);

    System.out.println("thenAcceptFuture.get():"+thenAcceptFuture.get());
    System.out.println("thenApplyFuture.get():"+thenApplyFuture.get());
    return  stringCompletableFuture.get() ;
  }
}
