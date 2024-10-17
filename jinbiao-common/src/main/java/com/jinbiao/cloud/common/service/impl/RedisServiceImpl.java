package com.jinbiao.cloud.common.service.impl;

import com.jinbiao.cloud.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * redis操作实现类
 * Created by macro on 2020/3/3.
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    void nilRecord(String cmd, String key) {
        CompletableFuture.supplyAsync(() -> {
            Throwable throwable = new Throwable();
            // log.warn("keyspace_misses " + key + " nil");
            String nil = "debug:redis:nil";
            StackTraceElement[] stack = throwable.getStackTrace();
            StringBuilder stringBuilder = new StringBuilder(String.format("%s: %s", cmd, key));
            for (StackTraceElement element : stack)
                stringBuilder.append(
                        String.format("\n%s:%d %s.%s", element.getFileName(), element.getLineNumber(), element.getClassName(), element.getMethodName())
                );
            lPush(nil, stringBuilder.toString());
            long pop = lSize(nil) - 100;//保留最近的100条
            for (int i = 0; i < pop; i++) rPop(nil);
            return null;
        });
    }

    @Override
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public void setKeyValidDay(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.DAYS);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object get(String key) {
        Object result = redisTemplate.opsForValue().get(key);
        if (result == null) nilRecord("GET", key);
        return result;
    }

    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    @Override
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        Long result = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        if (result == null || result == -2)
            nilRecord("PTTL/TTL", key);
        return result;
    }

    @Override
    public Boolean hasKey(String key) {
        Boolean result = redisTemplate.hasKey(key);
        if (Boolean.FALSE.equals(result)) nilRecord("EXISTS", key);
        return result;
    }

    @Override
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    @Override
    public Object hGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public Boolean hSet(String key, String hashKey, Object value, long time) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time);
    }

    @Override
    public void hSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Map<Object, Object> hGetAll(String key) {
        Map<Object, Object> result = redisTemplate.opsForHash().entries(key);
        if (CollectionUtils.isEmpty(result))
            nilRecord("HGETALL", key);
        return result;
    }

    @Override
    public Boolean hSetAll(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }

    @Override
    public void hSetAll(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hDel(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Set<Object> hKeys(String key) {
        Set<Object> result = redisTemplate.opsForHash().keys(key);
        if (CollectionUtils.isEmpty(result))
            nilRecord("HKEYS", key);
        return result;
    }

    @Override
    public Long hIncr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Long hDecr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    @Override
    public Set<Object> sMembers(String key) {
        Set<Object> result = redisTemplate.opsForSet().members(key);
        if (CollectionUtils.isEmpty(result) && Boolean.FALSE.equals(redisTemplate.hasKey(key)))
            nilRecord("SMEMBERS", key);
        return result;
    }

    @Override
    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long sAdd(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public Long sSize(String key) {
        Long result = redisTemplate.opsForSet().size(key);
        if (result == null || result == 0)
            nilRecord("SCARD", key);
        return result;
    }

    @Override
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long lSize(String key) {
        Long result = redisTemplate.opsForList().size(key);
        if (result == null || result == 0)
            nilRecord("LLEN", key);
        return result;
    }

    @Override
    public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long lPush(String key, Object value, long time) {
        Long index = redisTemplate.opsForList().leftPush(key, value);
        expire(key, time);
        return index;
    }

    @Override
    public Long rPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Long lPushAll(String key, Object... values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public Long lPushAll(String key, Long time, Object... values) {
        Long count = redisTemplate.opsForList().leftPushAll(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    @Override
    public Object rPop(String key) {
        Object result = redisTemplate.opsForList().rightPop(key);
        if (result == null)
            nilRecord("RPOP", key);
        return result;
    }

    @Override
    public Set<String> keys(String pattern) {
        // log.warn("Do not use KEYS command: KEYS {}", pattern);
        return redisTemplate.keys(pattern);
    }

    @Override
    public void addZSet(String key, String hashKey, double score) {
        redisTemplate.opsForZSet().add(key, hashKey, score);
    }

    @Override
    public Set<Object> rangeZSet(String key, Long begin, Long end) {
        return redisTemplate.opsForZSet().range(key, begin, end);
    }

    @Override
    public String getByStringSerial(String key) {
        try {
            return redisTemplate.execute((RedisCallback<String>) connction -> {
                RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
                byte[] serialize = stringSerializer.serialize(key);
                if (serialize == null) {
                    return null;
                }
                byte[] value = connction.get(serialize);
                return stringSerializer.deserialize(value);
            });
        } catch (Exception e) {
            // log.error("从缓存中读取信息异常", e);
            return null;
        }
    }

    @Override
    public void publishMsg(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }


    //public Boolean setNxExpire(String key, String value, Long time) {
    //
    //    return redisTemplate.opsForValue().setIfAbsent(key, value, time, TimeUnit.SECONDS);
    //
    //}
}
