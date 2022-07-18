package com.example.hystrix.feign;

import com.example.hystrix.feign.impl.FeignError;
import com.example.my_springcloud.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@FeignClient(value = "provider", fallback = FeignError.class)
public interface FeignProviderClient {

    @GetMapping("/student/findAll")
    Collection<Student> findAll();
}
