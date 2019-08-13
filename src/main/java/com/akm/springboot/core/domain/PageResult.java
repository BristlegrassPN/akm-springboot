package com.akm.springboot.core.domain;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "分页结果")
public class PageResult<T> {
    @ApiModelProperty(value = "总记录数")
    private long total;

    @ApiModelProperty(value = "结果集")
    private List<T> list;

    public PageResult(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.total = page.getTotal();
            this.list = page;
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
