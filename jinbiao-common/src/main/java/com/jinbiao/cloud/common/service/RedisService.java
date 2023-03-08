package com.jinbiao.cloud.common.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wangjinbiao
 * @date 2023/2/1 17:53
 * @desc
 */
public interface RedisService {

  /**
   * 保存属性
   */
  void set(String key, Object value, long time);


  /**
   * 设置key的剩余有效日
   *
   * @param key
   * @param value
   * @param time
   */
  void setKeyValidDay(String key, Object value, long time);


  /**
   * 保存属性
   */
  void set(String key, Object value);

  /**
   * 获取属性
   */
  Object get(String key);

  /**
   * 删除属性
   */
  Boolean del(String key);

  /**
   * 批量删除属性
   */
  Long del(List<String> keys);

  /**
   * 设置过期时间
   */
  Boolean expire(String key, long time);

  /**
   * 获取过期时间
   */
  Long getExpire(String key);

  /**
   * 判断是否有该属性
   */
  Boolean hasKey(String key);

  /**
   * 按delta递增
   */
  Long incr(String key, long delta);

  /**
   * 按delta递减
   */
  Long decr(String key, long delta);

  /**
   * 获取Hash结构中的属性
   */
  Object hGet(String key, String hashKey);

  /**
   * 向Hash结构中放入一个属性
   */
  Boolean hSet(String key, String hashKey, Object value, long time);

  /**
   * 向Hash结构中放入一个属性
   */
  void hSet(String key, String hashKey, Object value);

  /**
   * 直接获取整个Hash结构
   */
  Map<Object, Object> hGetAll(String key);

  /**
   * 直接设置整个Hash结构
   */
  Boolean hSetAll(String key, Map<String, Object> map, long time);

  /**
   * 直接设置整个Hash结构
   */
  void hSetAll(String key, Map<String, Object> map);

  /**
   * 删除Hash结构中的属性
   */
  void hDel(String key, Object... hashKey);

  /**
   * 判断Hash结构中是否有该属性
   */
  Boolean hHasKey(String key, String hashKey);

  /**
   * Hash结构中的属性列表
   */
  Set<Object> hKeys(String key);

  /**
   * Hash结构中属性递增
   */
  Long hIncr(String key, String hashKey, Long delta);

  /**
   * Hash结构中属性递减
   */
  Long hDecr(String key, String hashKey, Long delta);

  /**
   * 获取Set结构
   */
  Set<Object> sMembers(String key);

  /**
   * 向Set结构中添加属性
   */
  Long sAdd(String key, Object... values);

  /**
   * 向Set结构中添加属性
   */
  Long sAdd(String key, long time, Object... values);

  /**
   * 是否为Set中的属性
   */
  Boolean sIsMember(String key, Object value);

  /**
   * 获取Set结构的长度
   */
  Long sSize(String key);

  /**
   * 删除Set结构中的属性
   */
  Long sRemove(String key, Object... values);

  /**
   * 获取List结构中的属性
   */
  List<Object> lRange(String key, long start, long end);

  /**
   * 获取List结构的长度
   */
  Long lSize(String key);

  /**
   * 根据索引获取List中的属性
   */
  Object lIndex(String key, long index);

  /**
   * 向List结构中添加属性
   */
  Long lPush(String key, Object value);

  /**
   * 向List结构中添加属性
   */
  Long lPush(String key, Object value, long time);

  /**
   * 向List结构中添加属性
   */
  Long rPush(String key, Object value);

  /**
   * 向List结构中批量添加属性
   */
  Long lPushAll(String key, Object... values);

  /**
   * 向List结构中批量添加属性
   */
  Long lPushAll(String key, Long time, Object... values);

  /**
   * 从List结构中移除属性
   */
  Long lRemove(String key, long count, Object value);

  /**
   * 从List结构中获取属性
   *
   * @param key
   * @return
   */
  Object rPop(String key);

  /**
   * 获取key
   *
   * @param pattern
   * @return
   */
  @Deprecated
  Set<String> keys(String pattern);

  /**
   * 添加zSet
   *
   * @param key
   * @param hashKey
   * @param score
   */
  void addZSet(String key, String hashKey, double score);

  /**
   * 获取zSet中指定区间的数值，从小到大， end为-1时打印全部
   *
   * @param key
   * @param begin
   * @param end
   * @return
   */
  Set<Object> rangeZSet(String key, Long begin, Long end);

  /**
   * 向channel发布消息
   *
   * @param channel
   */
  void publishMsg(String channel, Object message);

  /**
   * 添加分布式锁
   *
   * @param key
   * @param value
   * @param time
   * @return
   */
  //Boolean setNxExpire(String key, String value, Long time);

  /**
   * 获取属性: 通过使用redis默认的序列化获取String类型的值:目的是为了解决不同系统数据存取异常问题。
   */
  String getByStringSerial(String key);
}
