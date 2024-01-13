package com.example.feign.config;

import com.example.my_springcloud.exception.feign_exception.FeignFailedResult;
import com.example.my_springcloud.exception.feign_exception.MyException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class FeignExceptionConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new UserErrorDecoder();
    }

    /**
     * 重新实现feign的异常处理，捕捉restful接口返回的json格式的异常信息
     */
    public static class UserErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {
            Exception exception = new MyException();
            ObjectMapper mapper = new ObjectMapper();
            //空属性处理
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
            //禁止使用int代表enum的order来反序列化enum
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
            try {
                String json = Util.toString(response.body().asReader());
                log.info("异常返回结果：" + json);
                exception = new RuntimeException(json);
                if (StringUtils.isEmpty(json)) {
                    return null;
                }
                FeignFailedResult result = mapper.readValue(json, FeignFailedResult.class);
                // 业务异常包装成自定义异常类MyException
                if (result.getCode() != 200) {
                    exception = new MyException(result.getMsg(), result.getCode());
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
            return exception;
        }

    }

}
