package com.akm.springboot.core.config;

import com.akm.springboot.core.domain.RestResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(annotations = RestController.class)
public class ResultHandlerAdvice implements ResponseBodyAdvice<Object> {
    private static final Logger logger = LoggerFactory.getLogger(ResultHandlerAdvice.class);

    private ThreadLocal<ObjectMapper> mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    //这个方法表示对于哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    // https://my.oschina.net/u/1757225/blog/1543715
    // https://karoy.cn/2019/03/07/SpringBoot-%E4%BD%BF%E7%94%A8ResponseBodyAdvice%E7%BB%9F%E4%B8%80%E5%8C%85%E8%A3%85RestController%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C/
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 表示接口本来要返回String类型,Sting类型不能直接转为RestResult对象，所以单独处理
        if ("org.springframework.http.converter.StringHttpMessageConverter".equals(aClass.getName())) {
            ObjectMapper mapper = mapperThreadLocal.get();
            try {
                return mapper.writeValueAsString(RestResult.success(body == null ? "" : body));
            } catch (JsonProcessingException e) {
                logger.error("类型转换失败", e);
            }
        }
        return RestResult.success(body);
    }
}


