package com.tang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableEurekaClient
public class HelloTest {

    @RequestMapping("/test")
    public String test() {
        return "success";
    }
}
