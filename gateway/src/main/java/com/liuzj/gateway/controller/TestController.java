package com.liuzj.gateway.controller;

import com.liuzj.gateway.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    TestService testService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return testService.hiService(name);
    }

    @RequestMapping(value = "toOut")
    public String toOut(){
        return "I am discoveryClient";
    }


}
