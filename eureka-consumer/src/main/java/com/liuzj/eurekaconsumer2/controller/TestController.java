package com.liuzj.eurekaconsumer2.controller;

import com.liuzj.eurekaconsumer2.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuzj
 * @date 2018-09-29
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${name:liuzj}")
    private String name;

    @Autowired
    TestService testService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return testService.hiService(name);
    }

    @RequestMapping(value = "toOut")
    public String toOut(){
        return "I am Tom";
    }

    @GetMapping(value = "getGitConfig")
    public String getGitConfig(){
        return name;
    }

    @PutMapping(value = {"/get/{userId}", "/get/{id}/{userId}"})
    public String getTest(@PathVariable(value = "userId")String userId, @PathVariable(value = "id", required = false)String id){
        return "userId：" + userId + " and id：" + id;
    }


}
