package com.akm.springboot.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义的配置参数
 *
 * @author bernix
 */
@ConfigurationProperties(prefix = "akm")
public class KitPropsConfig {

    /**
     * 配置中指定的cache-type为redis时的值
     */
    public static final String CACHE_TYPE_REDIS = "redis";

    private String cacheType;
    private String tokenSecretKey;

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public String getTokenSecretKey() {
        return tokenSecretKey;
    }

    public void setTokenSecretKey(String tokenSecretKey) {
        this.tokenSecretKey = tokenSecretKey;
    }
}