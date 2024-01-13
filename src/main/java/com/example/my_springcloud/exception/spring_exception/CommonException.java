package com.example.my_springcloud.exception.spring_exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{

    private String code;

    /**
     * 自己临时自定义状态码和状态信息
     *
     * @param code  状态码
     * @param message 状态信息
     */
    public CommonException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @param resultCode 从枚举对象中获取状态码和状态信息
     */
    public CommonException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }
}
