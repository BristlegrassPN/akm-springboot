package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(value = "com.akm.springboot.web.domain.sys.SysUserDetail")
@Data
public class SysUserDetail implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**
     * 是否启用(默认1,禁用0)
     */
    @ApiModelProperty(value = "是否启用(默认1,禁用0)")
    private Boolean enable;

    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    private Date expiredTime;

    private static final long serialVersionUID = 1L;

    private List<SysRole> roleList;
}