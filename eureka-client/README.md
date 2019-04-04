# SprinCloud
### 此为一般服务提供者，使用EurekaClient服务发现
#### 一.配置链路追踪
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
        #zipkin链路分析
        spring.zipkin.locator.discovery.enabled= true
        spring.zipkin.base-url= http://localhost:8799
        spring.zipkin.discovery-client-enabled=true
        spring.sleuth.sampler.probability=1.0
```