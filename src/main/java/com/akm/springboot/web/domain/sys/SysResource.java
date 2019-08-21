package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@ApiModel(value = "com.akm.springboot.web.domain.sys.SysResource")
@Data
public class SysResource implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String id;

    /**
     * 父id，0表示根节点
     */
    @ApiModelProperty(value = "父id，0表示根节点")
    private String parentId;

    /**
     * 资源类型1:菜单,2:按钮,3:其他
     */
    @ApiModelProperty(value = "资源类型1:菜单,2:按钮,3:其他")
    private Byte type;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    private String name;

    /**
     * 资源编码，前端根据该code控制资源的显示隐藏
     */
    @ApiModelProperty(value = "资源编码，前端根据该code控制资源的显示隐藏")
    private String code;

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
     * 应用类型1.web 2.app
     */
    @ApiModelProperty(value = "应用类型1.web 2.app")
    private Byte clientType;

    /**
     * 是否启用(默认1,禁用0)
     */
    @ApiModelProperty(value = "是否启用(默认1,禁用0)")
    private Boolean enable;

    private static final long serialVersionUID = 1L;
}