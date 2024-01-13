package com.example.my_springcloud.exception.spring_exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 621986096326899992L;

    private String message;

    private String errorCode;

    private T data;

    public BaseResult(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public static <T> BaseResult<T> success() {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setMessage(ResultCode.SUCCESS.getMsg());
        baseResult.setErrorCode(ResultCode.SUCCESS.getCode());
        return baseResult;
    }

    public static <T> BaseResult<T> success(T result) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setMessage(ResultCode.SUCCESS.getMsg());
        baseResult.setErrorCode(ResultCode.SUCCESS.getCode());
        baseResult.setData(result);
        return baseResult;
    }

    public static <T> BaseResult<T> fail(ResultCode error) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setMessage(error.getMsg());
        baseResult.setErrorCode(error.getCode());
        return baseResult;
    }

    public static <T> BaseResult<T> error(ResultCode error, String message) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setMessage(message);
        baseResult.setErrorCode(error.getCode());
        return baseResult;
    }

    public static <T> BaseResult<T> fail(ResultCode error, Exception e) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setMessage(e.getMessage());
        baseResult.setErrorCode(error.getCode());
        return baseResult;
    }

    public Boolean isSuccess() {
        return "0".equals(this.errorCode);
    }

}
