package com.akm.springboot.core.cache;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LocalCacheStorage<V> implements CacheStorage<V> {

    private final ConcurrentHashMap<String, CacheWrapper<V>> cache = new ConcurrentHashMap<>();

    @Override
    public V get(String key) {
        CacheWrapper<V> wrapper = cache.get(key);
        if (wrapper == null) {
            return null;
        }
        if (wrapper.isExpired()) {
            cache.remove(key);
            return null;
        }
        return wrapper.getCacheObject();
    }

    @Override
    public void delete(String key) {
        cache.remove(key);
    }

    @Override
    public boolean hasKey(String key) {
        return cache.containsKey(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        Pattern pattern1 = Pattern.compile(pattern);
        return cache.keySet().stream()
                .filter(pattern1.asPredicate())
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(Collection<String> keys) {
        for (String key : keys) {
            cache.remove(key);
        }
    }

    @Override
    public void set(String key, V value) {
        CacheWrapper<V> wrapper = new CacheWrapper<>(value);
        cache.put(key, wrapper);
    }

    @Override
    public void set(String key, V value, long timeout, TimeUnit unit) {
        CacheWrapper<V> wrapper;
        if (timeout > 0 && unit != null) {
            long expire = unit.toSeconds(timeout);
            wrapper = new CacheWrapper<>(value, expire);
        } else {
            wrapper = new CacheWrapper<>(value);
        }
        cache.put(key, wrapper);
    }

    @Override
    public boolean setIfAbsent(String key, V value) {
        CacheWrapper<V> wrapper = new CacheWrapper<>(value);
        return cache.putIfAbsent(key, wrapper) == null;
    }

    @Override
    public boolean expire(String key, long timeout, TimeUnit unit) {
        CacheWrapper<V> wrapper = cache.get(key);
        if (wrapper == null || wrapper.isExpired()) {
            return false;
        }
        long expire = unit.toSeconds(timeout);
        wrapper.setExpire(expire);
        return true;
    }

    @Override
    public Long getExpire(String key) {
        CacheWrapper<V> wrapper = cache.get(key);
        if (wrapper == null) {
            return -2L;
        }
        return wrapper.getExpire();
    }

    @Override
    public long time() {
        return System.currentTimeMillis();
    }
}
