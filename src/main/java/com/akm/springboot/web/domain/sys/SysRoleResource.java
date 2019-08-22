package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@ApiModel(value = "com.akm.springboot.web.domain.sys.SysRoleResource")
@Data
public class SysRoleResource implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * sys_role表id
     */
    @ApiModelProperty(value = "sys_role表id")
    private String roleId;

    /**
     * sys_resource表id
     */
    @ApiModelProperty(value = "sys_resource表id")
    private String resourceId;

    private static final long serialVersionUID = 1L;
}