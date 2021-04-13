package com.course.system.controller;


import com.course.server.domain.User;
import com.course.server.service.UserServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
//@EnableEurekaClient
@ComponentScan({"com.course.server.service"})

public class TestController {

    @Resource
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
