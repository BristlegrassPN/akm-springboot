package com.akm.springboot.core.config;

import com.akm.springboot.core.exception.CodeMsg;
import com.akm.springboot.core.utils.AssertUtils;
import com.akm.springboot.core.utils.CacheUtils;
import com.akm.springboot.core.utils.ThreadContext;
import com.akm.springboot.web.service.sys.SysApiService;
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
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final PathMatcher pathMatcher = new AntPathMatcher();
    private AkmPropsConfig akmPropsConfig;
    private SysApiService sysApiService;

    @Autowired
    AuthorizationInterceptor(AkmPropsConfig akmPropsConfig, SysApiService sysApiService) {
        this.akmPropsConfig = akmPropsConfig;
        this.sysApiService = sysApiService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestUri = request.getRequestURI();
        if (isOpenApi(requestUri)) {
            return true;
        }
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        AssertUtils.notBlank(token, CodeMsg.Unauthorized);
        Map<String, String> userInfo = CacheUtils.get(RedisKeys.TOKEN.concat(token));
        AssertUtils.notNull(userInfo, CodeMsg.TokenExpired);
        ThreadContext.set(AkmConstants.TOKEN, token);
        ThreadContext.set(AkmConstants.USER_ID, userInfo.get(AkmConstants.USER_ID));
        ThreadContext.set(AkmConstants.CLIENT_TYPE, userInfo.get(AkmConstants.CLIENT_TYPE));
        String roleId = userInfo.get(AkmConstants.CURRENT_ROLE_ID);
        ThreadContext.set(AkmConstants.CURRENT_ROLE_ID, roleId);
        if (isPublicApi(requestUri)) {
            return true;
        }
        AssertUtils.notNull(roleId, CodeMsg.Forbidden);
        AssertUtils.isTrue(isAccessApi(requestUri, roleId), CodeMsg.Forbidden);

        return true;
    }


    /**
     * 判断requestUri为open url
     * 若包含则不需要权限即可访问
     */
    private boolean isOpenApi(String requestUri) {
        if (requestUri.contains("/open/") || requestUri.equals("/error")) {
            return true;
        }
        List<String> openUrls = akmPropsConfig.getOpenUrls();
        if (openUrls != null && !openUrls.isEmpty()) {
            return openUrls.stream().anyMatch(urlPattern -> pathMatcher.match(urlPattern, requestUri));
        }
        return false;
    }

    /**
     * 判断requestUri是否为public url
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


    /**
     * 判断requestUri是否有权限访问
     */
    private boolean isAccessApi(String requestUri, String roleId) {
        // 根据roleId查询对应的api，判断该请求是否有权限
        List<String> uriList = sysApiService.getUriByRoleId(roleId);
        if (uriList == null || uriList.isEmpty()) {
            return false;
        }
        // 验证是否有任何匹配的url
        return uriList.stream().anyMatch(url -> pathMatcher.match(url, requestUri));
    }
}
