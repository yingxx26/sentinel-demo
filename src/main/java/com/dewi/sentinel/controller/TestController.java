package com.dewi.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guoyuting
 * @Date: Created at 2020/6/12 10:59 AM
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    @SentinelResource(value = "hello")
    public String hello() {

        return "hello world";
    }

}
