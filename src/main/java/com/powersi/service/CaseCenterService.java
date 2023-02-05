package com.powersi.service;


import com.powersi.entity.CaseCenter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface CaseCenterService {
    List<CaseCenter> getAllCase(Map map);

    Map getCaseInfoById(Long id) throws ExecutionException, InterruptedException;

    Map testFuture(Long id) throws InterruptedException, ExecutionException;

    Map testCompletableFuture(Long id) throws Exception;

    /**
     * 测试线程池任务是否执行完毕
     * @param id
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    Map testTaskIfFinished(Long id) throws InterruptedException, ExecutionException;
}
