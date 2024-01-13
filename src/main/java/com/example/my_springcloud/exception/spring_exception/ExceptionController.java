package com.example.my_springcloud.exception.spring_exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public BaseResult handlerException(CommonException e) {
        //异常返回false，Result是上一篇接口返回对象。
        return new BaseResult(e.getMessage(), e.getCode());
    }

}
