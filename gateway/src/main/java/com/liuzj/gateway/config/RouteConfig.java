package com.liuzj.gateway.config;

import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
        config.setParts(1);
        return builder.routes()
                .route("service-ribbon", r -> r.path("/api-a/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:8765"))
                .route("service-feign", r -> r.path("/api-b/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:8764"))
                // 以下配置无效，原因暂时未知
//                .route("service-feign", r -> r.path("/api-b/**")
//                        .filters(f -> f.addResponseHeader("X-AnotherHeader", "liuzj"))
//                        .uri("lb://service-feign") )
                .build();
    }

}
