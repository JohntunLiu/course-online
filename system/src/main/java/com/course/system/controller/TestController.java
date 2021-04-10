package com.course.system.controller;


import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "success";
    }
}
