package com.jinbiao.HelperClass;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author wangjinbiao
 * @date 2023/2/16 11:41
 * @desc
 */
public class SpliteArrayList {

  /**
   * 区间
   */
  private static int partition = 2;

  public static void spliteArrayList() {
    List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
    int total = list.size();
    List<List<Integer>> partitionList = Lists.newArrayList();
    int size = 0;
    List<Integer> dataList = Lists.newArrayList();
    for (Integer data : list) {
      if (size >= partition) {
        partitionList.add(dataList);
        dataList = Lists.newArrayList();
        size = 0;
      }
      size++;
      dataList.add(data);
      if (data.equals(list.get(total - 1)) && size < partition) {
        partitionList.add(dataList);
      }
    }
    System.out.println(partitionList);
  }

  public static void main(String[] args) {
    SpliteArrayList.spliteArrayList();

    //需要引入com.google.guava。pom文件 这里是用到了com.google.common.collect包下的集合工具类Lists：
    List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
    List<List<Integer>> partitionList = Lists.partition(list, partition);
    System.out.println(partitionList);
  }
}
