package com.liuzj.discoveryConsumer.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}
