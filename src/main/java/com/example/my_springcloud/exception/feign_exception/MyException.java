package com.example.my_springcloud.exception.feign_exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyException extends RuntimeException {

    // 自定义异常代码
    private int status = 503;

    public MyException(String message, int status) {
        super(message);
        this.status = status;
    }

}
