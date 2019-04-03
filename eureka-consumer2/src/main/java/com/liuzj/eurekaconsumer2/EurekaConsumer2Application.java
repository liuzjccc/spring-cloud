package com.liuzj.eurekaconsumer2;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuzj
 * @date 2018-09-28
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class EurekaConsumer2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumer2Application.class, args);
    }

    @Bean
    @LoadBalanced // 负载均衡开启
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean // 负载策略
    public IRule ribbonRule(){
        // 轮询(默认策略) return new RoundRobinRule();
        // 重试 return new RetryRule();
        // 随机
         return new RandomRule();
//        return new BestAvailableRule();// 根据并发量请求并发小的服务
    }

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}