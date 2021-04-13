package com.course.system.controller;


import com.course.system.domain.User;
import com.course.system.service.UserServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableEurekaClient
public class TestController {

    @Autowired
    private UserServiceTest userService;

    @RequestMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/user/{id}")
    public User queryById(@PathVariable Long id) {
        return userService.queryById(id);
    }



}
