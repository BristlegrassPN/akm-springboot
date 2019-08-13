package com.akm.springboot.core.exception;

/**
 * 自定义业务逻辑异常类
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2688655198558126042L;
    private Integer code;

    public BusinessException() {
    }

    public BusinessException(Integer code) {
        super();
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
