package com.akm.springboot.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * 在这里进行Spring MVC的配置, 如 interceptor, messageConverter, formatter, viewResolver等
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Resource
    private CorsInterceptor corsInterceptor;

    @Resource
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器需放在最上面
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");

        // 校验token的拦截器
        registry
                .addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/csrf",
                        "/"
                );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
