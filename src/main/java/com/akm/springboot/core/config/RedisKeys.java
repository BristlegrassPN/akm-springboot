package com.akm.springboot.core.config;

/**
 * 将所有的redis key统一定义在此, 避免key四处分散, 导致后期维护redis时无法找到所有的key
 */
public interface RedisKeys {
    /**
     * 项目中所有key的前缀, 避免不同项目访问同一个redis集群时出现重复key,
     * CacheUtils中的方法会自动加上此前缀, 调用CacheUtils, StringCacheUtils时key无需加此前缀
     */
    String PREFIX = "akm-springboot";

    /**
     * 互斥锁mutex的前缀, 完整的key为 PREFIX + NXKEY + key
     */
    String NXKEY = "nx:";

    /**
     * 某角色对应的Api uri列表key前缀, 完整的key为 PREFIX + ROLE_URI + role_code
     */
    String ROLE_URI = "role-uri:";

    /**
     * token前缀
     */
    String TOKEN = "token";
}
