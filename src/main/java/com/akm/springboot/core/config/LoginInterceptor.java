package com.akm.springboot.core.config;

import com.akm.springboot.core.utils.AssertUtils;
import com.akm.springboot.core.utils.CacheUtils;
import com.akm.springboot.core.utils.MapBuilder;
import com.akm.springboot.core.utils.StringCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final PathMatcher pathMatcher = new AntPathMatcher();
    private AkmPropsConfig akmPropsConfig;

    @Autowired
    LoginInterceptor(AkmPropsConfig akmPropsConfig) {
        this.akmPropsConfig = akmPropsConfig;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestUri = request.getRequestURI();
        System.out.println(requestUri);
        if (isOpenApi(requestUri)) {
            return true;
        }
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        AssertUtils.notBlank(token, "未提供token");
        String userId = StringCacheUtils.get(token);
        AssertUtils.notNull(userId, "token已过期");
        Object userObj = CacheUtils.get(userId);
        AssertUtils.notNull(userObj, "token已过期");
        MapBuilder<String, Object> mapBuilder  = (MapBuilder<String, Object>) userObj;
        Map<String, Object> userInfo = mapBuilder.build();
        if (isPublicApi(requestUri)) {
            return true;
        }
        Object roleObj = userInfo.get("roleList");
        AssertUtils.notNull(roleObj, "没有访问权限: " + requestUri);
        List<String> roleList = (List<String>) roleObj;
        for (String roleId : roleList) {
            System.out.println(roleId);
        }
        return true;
    }


    /**
     * 判断url为open url
     * 若包含则不需要权限即可访问
     */
    private boolean isOpenApi(String requestUri) {
        if (requestUri.contains("/open/")) {
            return true;
        }
        List<String> openUrls = akmPropsConfig.getOpenUrls();
        if (openUrls != null && !openUrls.isEmpty()) {
            return openUrls.stream().anyMatch(urlPattern -> pathMatcher.match(urlPattern, requestUri));
        }
        return false;
    }

    /**
     * 判断url是否为public url
     * 若包含则只需要登陆即可访问
     */
    private boolean isPublicApi(String requestUri) {
        if (requestUri.contains("/public/")) {
            return true;
        }
        List<String> publicUrls = akmPropsConfig.getPublicUrls();
        if (publicUrls != null && !publicUrls.isEmpty()) {
            return publicUrls.stream().anyMatch(urlPattern -> pathMatcher.match(urlPattern, requestUri));
        }
        return false;
    }
}
