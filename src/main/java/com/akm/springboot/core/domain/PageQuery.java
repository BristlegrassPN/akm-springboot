package com.akm.springboot.core.domain;

import com.akm.springboot.core.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "分页查询参数")
public class PageQuery<T> {
    private static final int DEFAULT_PAGE_NUM = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @ApiModelProperty(value = "当前页", example = "1")
    private Integer pageNum = DEFAULT_PAGE_NUM;

    @ApiModelProperty(value = "每页的数量", example = "10")
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    @ApiModelProperty(value = "排序参数，形如:createTime desc,name asc", example = "1")
    private String orderBy;

    @ApiModelProperty(value = "其他查询参数")
    private T condition;

    public int getPageNum() {
        return pageNum > 0 ? pageNum : DEFAULT_PAGE_NUM;
    }

    public void setPageNum(Integer pageNUm) {
        this.pageNum = pageNUm;
    }

    public int getPageSize() {
        return pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = StringUtils.hump2underline(orderBy, false);
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }
}
