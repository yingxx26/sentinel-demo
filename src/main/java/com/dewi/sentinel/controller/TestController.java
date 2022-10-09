package com.dewi.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: guoyuting
 * @Date: Created at 2020/6/12 10:59 AM
 */
@RestController
public class TestController {

    AtomicInteger a =new  AtomicInteger();

    @GetMapping("/hello")
    @SentinelResource(value = "hello",blockHandler = "blockHandlerMethod")
    public String hello() {

        return "hello world";
    }


    public String blockHandlerMethod(BlockException e){

        return "xianliu";
    }

    @GetMapping("/hello1")
    public String hello1() {
        a.getAndIncrement();
        if(a.get()%2==0){
         int i=1/0;
        }
        return "hello world  1";
    }

    @GetMapping("/hello2/{id}")//要用注解方式才有用
    public String hello2(@PathVariable("id") Integer id) {

        return "hello world  2";
    }
}
