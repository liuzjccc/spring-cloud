package com.liuzj.discoveryConsumer.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InfoSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String info) {
        String msg = info + ": " + new Date();
        System.out.println("Sender:"+msg);
        this.rabbitTemplate.convertAndSend("hello", msg);
    }
}
