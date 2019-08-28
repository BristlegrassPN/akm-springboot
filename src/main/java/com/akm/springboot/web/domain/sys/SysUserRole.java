package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value="com.akm.springboot.web.domain.sys.SysUserRole")
@Data
public class SysUserRole implements Serializable {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private String id;

    /**
    * sys_user表id
    */
    @ApiModelProperty(value="sys_user表id")
    private String userId;

    /**
    * sys_role表id
    */
    @ApiModelProperty(value="sys_role表id")
    private String roleId;

    private static final long serialVersionUID = 1L;
}