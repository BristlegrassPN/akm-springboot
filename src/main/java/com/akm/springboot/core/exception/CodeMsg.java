package com.akm.springboot.core.exception;

public enum CodeMsg {
    Unauthorized(401, "未提供token"),
    TokenExpired(401, "token已过期，请重新登陆"),
    Forbidden(403, "没有访问权限");

    private int code;
    private String msg;

    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
