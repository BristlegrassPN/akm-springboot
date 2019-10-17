package com.akm.springboot.core.cache;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface CacheStorage<V> {

    /**
     * 获取key对应的缓存值
     *
     * @param key
     * @return key对应的缓存值
     */
    V get(String key);

    /**
     * 删除指定key的缓存
     *
     * @param key
     */
    void delete(String key);

    /**
     * 判断指定的key是否存在
     *
     * @param key
     * @return 若缓存中有此key, 则返回{@code true}
     */
    boolean hasKey(String key);

    /**
     * 根据正则表达模版, 获取符合的key列表
     *
     * @param pattern
     * @return 匹配的key集合
     */
    Set<String> keys(String pattern);

    /**
     * 批量删除集合里指定的缓存
     *
     * @param keys
     */
    void delete(Collection<String> keys);

    /**
     * 设置key对应的缓存值
     *
     * @param key
     * @param value
     */
    void set(String key, V value);

    /**
     * 设置key对应的缓存, 并指定失效时间
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    void set(String key, V value, long timeout, TimeUnit unit);

    /**
     * Set key to hold the value if key is absent
     *
     * @param key
     * @param value
     * @return {@code true} if value is set successfully
     */
    boolean setIfAbsent(String key, V value);

    /**
     * set time to live for given key
     *
     * @param key
     * @param timeout
     * @param unit
     * @return {@code true} if set successfully
     */
    boolean expire(String key, long timeout, TimeUnit unit);


    /**
     * 获取缓存还有多久失效
     * 当 key 不存在时，返回 -2 。
     * 当 key 存在但没有设置剩余生存时间时，返回 -1 。
     * 否则，以秒为单位，返回 key 的剩余生存时间。
     *
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 获取缓存服务器的系统时间
     *
     * @return 系统时间（毫秒）
     */
    long time();
}
