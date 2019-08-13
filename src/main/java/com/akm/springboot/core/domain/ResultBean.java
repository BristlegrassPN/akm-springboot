package com.akm.springboot.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "返回对象")
public class ResultBean<T> {

    public static final Integer SUCCESS = 1;
    public static final Integer FAIL = 0;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "响应信息")
    private String msg;

    @ApiModelProperty(value = "响应的数据")
    private T result;

    public static ResultBean success() {
        return success(null);
    }

    public static <E> ResultBean<E> success(E result) {
        ResultBean<E> bean = new ResultBean<>();
        bean.code = SUCCESS;
        bean.result = result;
        return bean;
    }

    public static ResultBean fail(String msg) {
        ResultBean bean = new ResultBean<>();
        bean.code = FAIL;
        bean.msg = msg;
        return bean;
    }

    public static ResultBean fail(Integer code, String msg) {
        ResultBean bean = new ResultBean<>();
        bean.code = (code == null || code.equals(SUCCESS)) ? FAIL : code;
        bean.msg = msg;
        return bean;
    }

    public static <E> ResultBean<E> fail(Integer code, String msg, E result) {
        ResultBean<E> bean = new ResultBean<>();
        bean.code = (code == null || code.equals(SUCCESS)) ? FAIL : code;
        bean.msg = msg;
        bean.result = result;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}