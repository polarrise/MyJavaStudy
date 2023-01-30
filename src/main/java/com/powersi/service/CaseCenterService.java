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
}
