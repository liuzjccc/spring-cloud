package com.liuzj.discoveryConsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SERVICE-HI")
public interface TestService {

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    String hiService(@RequestParam String name);

}
