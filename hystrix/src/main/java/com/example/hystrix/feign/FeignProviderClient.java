package com.example.hystrix.feign;

import com.example.hystrix.feign.impl.FeignError;
import com.example.my_springcloud.entity.Student;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collection;

@FeignClient(value = "provider", fallback = FeignError.class)
//@FeignClient(value = "provider", fallback = FeignCallbackFactory.class)
public interface FeignProviderClient {

    @GetMapping("/student/findAll")
    Collection<Student> findAll();
}

@Component
class FeignCallbackFactory implements FallbackFactory<FeignProviderClient> {

    @Override
    public FeignProviderClient create(Throwable throwable) {
        return () -> {
            System.out.println(throwable.getMessage());
            Collection<Student> s = new ArrayList<>();
            s.add(new Student(100L, "开课", 1100));
            return s;
        };
    }
}