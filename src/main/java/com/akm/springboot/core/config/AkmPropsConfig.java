package com.akm.springboot.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 自定义的配置参数
 *
 * @author bernix
 */
@ConfigurationProperties(prefix = "akm")
public class AkmPropsConfig {

    /**
     * 配置中指定的cache-type为redis时的值
     */
    public static final String CACHE_TYPE_REDIS = "redis";

    private String cacheType;
    private String tokenSecretKey;
    private List<String> openUrls;
    private List<String> publicUrls;

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

    public List<String> getOpenUrls() {
        return openUrls;
    }

    public void setOpenUrls(List<String> openUrls) {
        this.openUrls = openUrls;
    }

    public List<String> getPublicUrls() {
        return publicUrls;
    }

    public void setPublicUrls(List<String> publicUrls) {
        this.publicUrls = publicUrls;
    }
}