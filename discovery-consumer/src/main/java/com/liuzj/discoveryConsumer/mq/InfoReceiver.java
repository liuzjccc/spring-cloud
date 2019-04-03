package com.liuzj.discoveryConsumer.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class InfoReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("Receiver:"+msg);
    }
}
