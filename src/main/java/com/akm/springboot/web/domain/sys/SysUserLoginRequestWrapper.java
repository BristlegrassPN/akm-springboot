package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModelProperty;

/**
 * 登录信息
 */
public class SysUserLoginRequestWrapper {
    @ApiModelProperty(value = "客户端类型类型,对应字典表client_type,由于前端分离,后台系统可能要对接多个客户端,如web，app", required = true)
    private Byte clientType;

    @ApiModelProperty(value = "用户名", required = true, position = 1)
    private String username;

    @ApiModelProperty(value = "加密的密码", required = true, position = 2)
    private String password;

    public Byte getClientType() {
        return clientType;
    }

    public void setClientType(Byte clientType) {
        this.clientType = clientType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
