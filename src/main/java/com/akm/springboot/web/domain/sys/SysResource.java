package com.akm.springboot.web.domain.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "com.akm.springboot.web.domain.sys.SysResource")
@Data
public class SysResource implements Serializable {
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
     * 菜单uri
     */
    @ApiModelProperty(value = "菜单uri")
    private String uri;

    /**
     * 资源编码，前端根据该code控制资源的显示隐藏
     */
    @ApiModelProperty(value = "资源编码，前端根据该code控制资源的显示隐藏")
    private String code;

    /**
     * 资源图标class
     */
    @ApiModelProperty(value = "资源图标class")
    private String icon;

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
     * 应用类型,对应字典表client_type,由于前端分离,后台系统可能要对接多个客户端,如web，app
     */
    @ApiModelProperty(value = "应用类型,对应字典表client_type,由于前端分离,后台系统可能要对接多个客户端,如web，app")
    private Byte clientType;

    /**
     * 是否启用(默认1,禁用0)
     */
    @ApiModelProperty(value = "是否启用(默认1,禁用0)")
    private Boolean enable;

    private static final long serialVersionUID = 1L;

    private List<String> apiIdList;
}