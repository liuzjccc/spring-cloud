package com.liuzj.discoveryConsumer.controller;

import com.liuzj.discoveryConsumer.mq.InfoSender;
import com.liuzj.discoveryConsumer.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Autowired
    private InfoSender infoSender;

    @Resource
    TestService testService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam(name = "name") String name) {
        return testService.hiService(name);
    }

    @RequestMapping(value = "toOut")
    public String toOut(){
        return "I am discoveryClient";
    }

    @GetMapping(value = "infoSend")
    public String infoSend(@RequestParam String info){
        infoSender.send(info);
        return "消息发送成功！";
    }

}
