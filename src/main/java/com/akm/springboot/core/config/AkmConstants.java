package com.akm.springboot.core.config;

/**
 * 项目中用到的一些常量，注意和RedisKeys区分
 */
public interface AkmConstants {
    // 用户登陆的token
    String TOKEN = "token";

    // 用户的访问类型（客户端类型）
    String CLIENT_TYPE = "clientType";

    // 用户id
    String USER_ID = "userId";

    // 用户名
    String USERNAME = "username";

    // 登陆用户的当前使用角色
    String CURRENT_ROLE_ID = "currentRoleId";
}
