package com.example.ribbon.controller;

import com.example.my_springcloud.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collection;

@RestController
@RequestMapping("ribbon")
public class RibbonController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/findAll")
    public Collection<Student> findAll() {
        return restTemplate.getForObject("http://eureka-client-a/student/findAll", Collection.class);
    }
}
