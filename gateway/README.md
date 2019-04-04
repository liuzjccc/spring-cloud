# SprinCloud
### gateway 和 zuul 相似，都是网关路由，可以进行请求过滤，权限控制;但Zuul只是Spring cloud整合Netflix的，gateway才是Spring cloud自己的网关
#### 一.Gateway配置步骤
>* 引入相关的包
```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-feign</artifactId>
            <version>1.4.5.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
            <version>2.0.0.RELEASE</version>
        </dependency>
        
        <!-- 排除 spring-boot-starter-web 依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>*</groupId>
                    <artifactId>*</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```
>* 在启动类上加上@EnableDiscoveryClient、@EnableFeignClients两个注解
>* 添加路由配置
```
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
```
#### 二.Gateway配置全局过滤器
```
        @Component
        public class GateWayGlobalFilter  implements GlobalFilter, Ordered {
        
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String token = exchange.getRequest().getQueryParams().getFirst("authToken");
                if (token == null || token.isEmpty()) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    ServerHttpResponse response = exchange.getResponse();
                    Map<String,Object> result = new HashMap<>();
                    result.put("status", HttpStatus.UNAUTHORIZED.value());
                    result.put("data",  HttpStatus.UNAUTHORIZED.getReasonPhrase());
                    byte[] bits = result.toString().getBytes(StandardCharsets.UTF_8);
                    DataBuffer buffer = response.bufferFactory().wrap(bits);
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    //指定编码，否则在浏览器中会中文乱码
                    response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
                    return response.writeWith(Mono.just(buffer));
                }
                return chain.filter(exchange);
            }
        
            @Override
            public int getOrder() {
                return 0;
            }
        }
```
#### 三.配置链路追踪
>* 添加相关依赖
```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
            <version>2.0.0.RELEASE</version>
        </dependency>
```
>* 添加配置
```
        spring:
          zipkin:
            enabled: true
            locator:
              discovery:
                enabled: true
            base-url: http://localhost:8799
          sleuth:
            sampler:
              probability: 1.0
```