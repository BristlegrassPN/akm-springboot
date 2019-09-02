package com.akm.springboot.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Convenient builder class to build a map in fluent mode.
 *
 * @author <a href="mailto:ningyaobai@gzkit.com.cn">bernix</a>
 * 星期四, 八月 31, 2017
 * @version 1.0
 */
public class MapBuilder<K, V> {
    final private Map<K, V> map;

    private MapBuilder() {
        map = new HashMap<>();
    }

    public static <K, V> MapBuilder<K, V> create() {
        return new MapBuilder<>();
    }

    public static MapBuilder<String, Object> createDefault() {
        return create();
    }

    public static MapBuilder<String, String> createString() {
        return create();
    }

    public MapBuilder<K, V> put(K key, V value) {
        map.put(key, value);
        return this;
    }

    public MapBuilder<K, V> remove(K key) {
        map.remove(key);
        return this;
    }

    public MapBuilder<K, V> putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
        return this;
    }

    public Map<K, V> build() {
        return map;
    }
}
