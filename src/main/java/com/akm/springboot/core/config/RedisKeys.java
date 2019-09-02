package com.akm.springboot.core.config;

/**
 * 将所有的redis key统一定义在此, 避免key四处分散, 导致后期维护redis时无法找到所有的key
 *
 * @author <a href="mailto:ningyaobai@gzkit.com.cn">bernix</a>
 * 星期三, 九月 13, 2017
 * @version 1.0
 */
public interface RedisKeys {
    /**
     * 项目中所有key的前缀, 避免不同项目访问同一个redis集群时出现重复key,
     * CacheUtils中的方法会自动加上此前缀, 调用CacheUtils, StringCacheUtils时key无需加此前缀
     */
    String PREFIX = "backend.service-platform:";

    /**
     * 互斥锁mutex的前缀, 完整的key为 PREFIX + NXKEY + key
     */
    String NXKEY = "nx:";

    /**
     * jwt token黑名单的前缀, 完整的key为 PREFIX + JTI_BLACKLIST + jti
     */
    String JTI_BLACKLIST = "jti-blacklist:";

    /**
     * 某权限角色的permission列表key前缀, 完整的key为 PREFIX + ROLE_PERMISSIONS + role_code
     */
    String ROLE_PERMISSIONS = "role-permissions:";
}
