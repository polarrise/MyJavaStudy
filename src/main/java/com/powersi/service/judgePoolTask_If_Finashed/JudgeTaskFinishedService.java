package com.powersi.service.judgePoolTask_If_Finashed;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author wangjinbiao
 * @date 2023/2/3 14:40
 * @desc 通过策略模式-三个办法去判断线程池任务是否执行完成
 */
public interface JudgeTaskFinishedService {
  Map judgeTaskFinashed(Long id) throws ExecutionException, InterruptedException;
}
