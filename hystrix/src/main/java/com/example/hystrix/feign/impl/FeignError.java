package com.example.hystrix.feign.impl;

import com.example.hystrix.feign.FeignProviderClient;
import com.example.my_springcloud.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class FeignError implements FeignProviderClient {

    @Override
    public Collection<Student> findAll() {
        Collection<Student> s = new ArrayList<>();
        s.add(new Student(100L, "开课", 1100));
        return s;
    }
}
