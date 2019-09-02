package com.akm.springboot.core.cache;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:ningyaobai@gzkit.com.cn">bernix</a>
 * 星期一, 十月 09, 2017
 * @version 1.0
 */
public class StringRedisCacheStorage implements CacheStorage<String> {
    private final StringRedisTemplate stringRedisTemplate;

    public StringRedisCacheStorage(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    @Override
    public void delete(Collection<String> keys) {
        stringRedisTemplate.delete(keys);
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public boolean setIfAbsent(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.expire(key, timeout, unit);
    }

    @Override
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    @Override
    public long time() {
        RedisConnection redisConnection;
        try {
            redisConnection = stringRedisTemplate.getConnectionFactory().getConnection();
        } catch (Exception e) {
            redisConnection = stringRedisTemplate.getConnectionFactory().getClusterConnection();
        }
        try {
            long time = redisConnection.time();
            return time;
        } finally {
            redisConnection.close();
        }
    }
}
