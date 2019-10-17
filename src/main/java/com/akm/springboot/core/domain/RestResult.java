package com.akm.springboot.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "返回对象")
public class RestResult<T> {

    private static final Integer SUCCESS = 1;
    private static final Integer FAIL = 0;

    @ApiModelProperty(value = "状态码，非1则失败")
    private Integer code = SUCCESS;

    @ApiModelProperty(value = "响应信息")
    private String msg = "";

    @ApiModelProperty(value = "响应的数据")
    private T data;

    public static <E> RestResult<E> success(E result) {
        RestResult<E> bean = new RestResult<>();
        bean.code = SUCCESS;
        bean.data = result;
        return bean;
    }

    public static RestResult fail(String msg) {
        RestResult bean = new RestResult<>();
        bean.code = FAIL;
        bean.msg = msg;
        return bean;
    }

    public static RestResult fail(Integer code, String msg) {
        RestResult bean = new RestResult<>();
        bean.code = (code == null || code.equals(SUCCESS)) ? FAIL : code;
        bean.msg = msg;
        return bean;
    }

    public static <E> RestResult<E> fail(Integer code, String msg, E data) {
        RestResult<E> bean = new RestResult<>();
        bean.code = (code == null || code.equals(SUCCESS)) ? FAIL : code;
        bean.msg = msg;
        bean.data = data;
        return bean;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}