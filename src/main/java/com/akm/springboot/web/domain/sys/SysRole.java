package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel(value="com.akm.springboot.web.domain.sys.SysRole")
@Data
public class SysRole implements Serializable {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private String id;

    /**
    * 角色名称
    */
    @ApiModelProperty(value="角色名称")
    private String name;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
    * 排序（倒序）
    */
    @ApiModelProperty(value="排序（倒序）")
    private Integer seq;

    /**
    * 应用类型,对应字典表client_type,由于前端分离,后台系统可能要对接多个客户端,如web，app
    */
    @ApiModelProperty(value="应用类型,对应字典表client_type,由于前端分离,后台系统可能要对接多个客户端,如web，app")
    private Byte clientType;

    /**
    * 是否启用(默认1,禁用0)
    */
    @ApiModelProperty(value="是否启用(默认1,禁用0)")
    private Boolean enable;

    private static final long serialVersionUID = 1L;

    private List<String> resourceIdList;
}