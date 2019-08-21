package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@ApiModel(value = "com.akm.springboot.web.domain.sys.SysApi")
@Data
public class SysApi implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 父id，0表示根节点
     */
    @ApiModelProperty(value = "父id，0表示根节点")
    private String parentId;

    /**
     * api接口名称
     */
    @ApiModelProperty(value = "api接口名称")
    private String name;

    /**
     * service接口的权限验证规则，如：/user/op/save、/user/op/**
     */
    @ApiModelProperty(value = "service接口的权限验证规则，如：/user/op/save、/user/op/**")
    private String uri;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 排序（倒序）
     */
    @ApiModelProperty(value = "排序（倒序）")
    private Integer seq;

    private static final long serialVersionUID = 1L;
}