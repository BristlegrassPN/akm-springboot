package com.akm.springboot.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;

@Configuration
public class RedisConfig {

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisSerializer<Object> serializer = jsonRedisSerializer();
        // explicitly set the jackson serializer in redis template
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(serializer);
        template.setValueSerializer(serializer);
        return template;
    }

    private GenericJackson2JsonRedisSerializer jsonRedisSerializer() {
        // 注: Spring Data Redis支持JDK序列化, Json序列化, XML序列化
        // - JDK序列化: 默认采用JDK序列化方式的类JdkSerializationRedisSerializer, 速度快但占用空间较大,
        //   对象必须实现java.io.Serializable接口
        // - Json序列化: 空间占用小, 包括GenericJackson2JsonRedisSerializer和Jackson2JsonRedisSerializer 两种,
        //   其中GenericJackson2JsonRedisSerializer无需为各种类型单独定义Bean
        // - XML序列化: OxmSerializer, 速度慢空间占用较大
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        return new GenericJackson2JsonRedisSerializer(mapper);
    }

}
