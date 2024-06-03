package com.example.mass.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
@Api(value = "Article Controller", description = "测试接口")
public class Hello {

    @PostMapping("/test")
    public String Hello() {
        return "hello";
    }
}
