# SprinCloud
#### zipkin链路追踪
##### 一、配置步骤
>* 引入相应的包
```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        # 需要排除 log4j2 否则启动报错，包冲突
        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin-server</artifactId>
            <version>2.11.11</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-log4j2</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin-autoconfigure-ui</artifactId>
            <version>2.11.11</version>
            <scope>runtime</scope>
        </dependency>
```
>* 在Application上加上@EnableZipkinServer注解

>* 配置
```
    # 解決报错：Prometheus requires that all meters with the same name have the same set of tag keys
    management.metrics.web.server.auto-time-requests=false
```
>* 目前只有gateway服务能被链路检测到，其他的无法被检测到，问题原因还未找到

>* ![Image](../images/zipkin.png)
