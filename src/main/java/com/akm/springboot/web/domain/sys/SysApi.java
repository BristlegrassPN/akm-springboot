package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "com.akm.springboot.web.domain.sys.SysApi")
@Data
public class SysApi implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Integer parentId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
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

    /**
     * 删除标志，默认0，删除1
     */
    @ApiModelProperty(value = "删除标志，默认0，删除1")
    private Byte del;

    private static final long serialVersionUID = 1L;
}