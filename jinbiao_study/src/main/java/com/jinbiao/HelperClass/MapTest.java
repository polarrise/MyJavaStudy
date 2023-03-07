package com.jinbiao.HelperClass;

import java.util.*;

/**
 * @author wangjinbiao
 * @date 2022/11/21$ 11:29$
 * @desc
 */
public class MapTest {
  public static void main(String[] args) {
    List<Integer> followerList = Arrays.asList(1,2,3,4,5);
    HashMap<Object, Object> redisOutHashMap = new HashMap<>(followerList.size());
    redisOutHashMap.put("1",1);
    redisOutHashMap.put("2",2);
    redisOutHashMap.put("3",3);
    redisOutHashMap.put("4",3);
    redisOutHashMap.put("5",3);
    redisOutHashMap.put("assignFlag",0);

    Object object = redisOutHashMap;
    Map<Object, Object> cacheData = new HashMap<>(followerList.size());
    if(object != null){
      cacheData = (Map<Object, Object>)object;
    }
    if(cacheData.isEmpty()){
      System.out.println("无缓存数据");
    }else{
      System.out.println("有缓存数据");
    }
  }
}
