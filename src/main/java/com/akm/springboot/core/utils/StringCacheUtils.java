package com.akm.springboot.core.utils;

import com.akm.springboot.core.cache.CacheStorage;
import com.akm.springboot.core.cache.LocalCacheStorage;
import com.akm.springboot.core.cache.StringRedisCacheStorage;
import com.akm.springboot.core.config.AkmPropsConfig;
import com.akm.springboot.core.config.RedisKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 缓存相关的帮助类, 提供了操作缓存的公用方法。
 * <p>
 * 此类中的方法基于StringRedisTemplate, key和value都采用StringRedisSerializer序列化策略。
 * </p>
 *
 * @author <a href="mailto:ningyaobai@gzkit.com.cn">bernix</a>
 * 星期二, 九月 12, 2017
 * @version 1.0
 */
public class StringCacheUtils {

    private static final Logger logger = LoggerFactory.getLogger(StringCacheUtils.class);

    private static CacheStorage<String> cacheStorage;

    static {
        AkmPropsConfig kitProps = SpringContextHolder.getBean(AkmPropsConfig.class);
        if (kitProps != null && AkmPropsConfig.CACHE_TYPE_REDIS.equals(kitProps.getCacheType())) {
            cacheStorage = new StringRedisCacheStorage(SpringContextHolder.getBean(StringRedisTemplate.class));
            logger.info(">> using REDIS as cache.");
        } else {
            cacheStorage = new LocalCacheStorage<>();
            logger.info(">> using LOCAL memory as cache.");
        }
    }

    /**
     * Don't let anyone instantiate this class
     */
    private StringCacheUtils() {
    }

    /**
     * @param key 无需前缀,方法里会自动加上
     * @return
     */
    public static Integer getInt(String key) {
        String value = cacheStorage.get(CacheUtils.prefixKey(key));
        return StringUtils.isBlank(value) ? null : Integer.valueOf(value);
    }

    /**
     * @param key 无需前缀,方法里会自动加上
     * @return
     */
    public static String get(String key) {
        return cacheStorage.get(CacheUtils.prefixKey(key));
    }

    /**
     * @param key   无需前缀,方法里会自动加上
     * @param value
     */
    public static void set(String key, String value) {
        cacheStorage.set(CacheUtils.prefixKey(key), value);
    }

    /**
     * 设置缓存值，并指定失效时间
     *
     * @param key     无需前缀,方法里会自动加上
     * @param value
     * @param timeout 失效时间(秒)
     */
    public static void set(String key, String value, long timeout) {
        set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存值，并指定失效时间
     *
     * @param key     无需前缀,方法里会自动加上
     * @param value
     * @param timeout
     * @param unit
     */
    public static void set(String key, String value, long timeout, TimeUnit unit) {
        cacheStorage.set(CacheUtils.prefixKey(key), value, timeout, unit);
    }

    /**
     * 删除指定的缓存
     *
     * @param key 无需前缀,方法里会自动加上
     */
    public static void del(String key) {
        cacheStorage.delete(CacheUtils.prefixKey(key));
    }

    /**
     * 判断key是否已存在
     *
     * @param key
     * @return
     */
    public static boolean hasKey(String key) {
        return cacheStorage.hasKey(CacheUtils.prefixKey(key));
    }

    /**
     * set if not exist, 不存在时才会设置
     *
     * @param key   无需前缀,方法里会自动加上
     * @param value
     * @return
     */
    public static boolean setnx(String key, String value) {
        return setnx(key, value, -1);
    }

    /**
     * set if not exist, 不存在时才会设置, 并指定失效时间(秒)
     *
     * @param key   无需前缀,方法里会自动加上
     * @param value
     * @param ttl   大于0才起作用
     * @return
     */
    public static boolean setnx(String key, String value, long ttl) {
        String nxkey = CacheUtils.prefixKey(key);
        boolean flag = cacheStorage.setIfAbsent(nxkey, value);
        if (flag && ttl > 0) {
            cacheStorage.expire(nxkey, ttl, TimeUnit.SECONDS);
        }
        return flag;
    }

    /**
     * 从缓存获取对应key的值, 若缓存里没有, 则从 valueLoader 获取, 并回设到缓存里.
     * <p>
     * 此方法增加了保护策略，使用一个简单的分布式锁(互斥锁mutex)防止缓存被击穿。简单地来说，就是在缓存失效的时候（判断拿出来的值为空），
     * 不是立即去load db，而是先使用缓存工具的某些带成功操作返回值的操作（比如Redis的SETNX或者Memcache的ADD）去set一个mutex key，
     * 当操作返回成功时，再进行load db的操作并回设缓存；否则，就重试整个get缓存的方法。
     * </p>
     * <p>
     * 缓存击穿: 缓存在某个时间点过期的时候，恰好在这个时间点对这个Key有大量的并发请求过来，
     * 这些请求发现缓存过期一般都会从后端DB加载数据并回设到缓存，这个时候大并发的请求可能会瞬间把后端DB压垮。
     * </p>
     *
     * @param key         无需前缀,方法里会自动加上
     * @param timeout     将值回设到缓存时的失效时间(秒), 设置为-1或0表示永不失效
     * @param valueLoader 缓存值不存在时,获取该值的方法
     * @return
     */
    public static String cacheAndGet(String key, long timeout, Supplier<String> valueLoader) {
        String redisKey = CacheUtils.prefixKey(key);
        String value = cacheStorage.get(redisKey);
        if (value == null) {
            // 有可能此缓存值就是null, 所以先检查是否有key
            if (cacheStorage.hasKey(redisKey)) {
                return null;
            }
            // 每个key都有自己的nx锁
            String nxkey = RedisKeys.NXKEY.concat(key);
            // 设置互斥锁的超时时间, 防止del操作失败导致缓存过期后一直不能load db
            if (setnx(nxkey, CacheUtils.MUTEX_VALUE, CacheUtils.MUTEX_EXPIRE_SEC)) {
                // 从database获取值
                value = valueLoader.get();
                // 注: 就算值是null，也设置此值, 避免没有这个key导致每次读取都要进入DB
                if (timeout <= 0) {
                    cacheStorage.set(redisKey, value);
                } else {
                    cacheStorage.set(redisKey, value, timeout, TimeUnit.SECONDS);
                }
                // 释放互斥锁并返回值
                del(nxkey);
                return value;
            } else { // 获取互斥锁失败, 说明另一个线程已经在进行load db
                if (CacheUtils.reachMaxRetryCount(key)) {
                    logger.error(">> 获取缓存值时重试次数过多, key: {}", key);
                    return null;
                }
                try {
                    // 等待一段时间后再重试获取缓存值
                    Thread.sleep(200);
                    return cacheAndGet(key, timeout, valueLoader);
                } catch (InterruptedException e) {
                    logger.error("thread sleep error", e);
                    throw new RuntimeException("获取缓存值失败", e);
                }
            }
        } else {
            return value;
        }
    }

}
