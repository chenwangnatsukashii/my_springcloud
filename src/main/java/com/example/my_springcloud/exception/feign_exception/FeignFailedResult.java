package com.example.my_springcloud.exception.feign_exception;

import lombok.Data;

@Data
public class FeignFailedResult {
    private String msg;
    private int code;
}
