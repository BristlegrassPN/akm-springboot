package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "com.akm.springboot.web.domain.sys.SysDict")
@Data
public class SysDict implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 字典编码（英文，多个单词下划线分隔）
     */
    @ApiModelProperty(value = "字典编码（英文，多个单词下划线分隔）")
    private String type;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称")
    private String typeName;

    /**
     * 名称（用于给用户展示）
     */
    @ApiModelProperty(value = "名称（用于给用户展示）")
    private String label;

    /**
     * 值（业务表保存该值）
     */
    @ApiModelProperty(value = "值（业务表保存该值）")
    private Integer value;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;

    private static final long serialVersionUID = 1L;
}