package com.jinbiao.HelperClass;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author wangjinbiao
 * @date 2023/1/17$ 10:46$
 * @desc
 */
public class BatchSplitInsert {

  /**
   * 单次插入--最大插入量
   */
  private final int maxInsertItemNumPerTime = 400;

  public <T> void batchSplitInsert(List<T> list, Consumer<List<T>> insertFunc) {
    List<List<T>> all = new ArrayList<>();
    if (list.size() > maxInsertItemNumPerTime) {
      int i = 0;
      while (i < list.size()) {
        List<T> subList;
        if (i + maxInsertItemNumPerTime > list.size()){
          subList = list.subList(i, list.size());
        }else {
          subList = list.subList(i, i + maxInsertItemNumPerTime);
        }
        i = i + maxInsertItemNumPerTime;
        all.add(subList);
      }
      all.parallelStream().forEach(insertFunc);
    } else {
      insertFunc.accept(list);
    }
  }

  public static void main(String[] args) {
    List<Integer> list = new ArrayList(1000);
    for (int i =1; i<=1100;i++){
      list.add(i);
    }

    Consumer<List<Integer>> consumer =new Consumer<List<Integer>>() {
      @Override
      public void accept(List<Integer> o) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(o);
        System.out.println("执行插入语句: 插入数据条数:"+o.size());
      }
    };

    new BatchSplitInsert().batchSplitInsert(list ,consumer);
  }
}
