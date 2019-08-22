package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "com.akm.springboot.web.domain.sys.SysResourceApi")
@Data
public class SysResourceApi implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * sys_resource表id
     */
    @ApiModelProperty(value = "sys_resource表id")
    private String resourceId;

    /**
     * sys_api表id
     */
    @ApiModelProperty(value = "sys_api表id")
    private String apiId;

    private static final long serialVersionUID = 1L;
}