# 配置服务器
### 一.服务器搭建步骤
>* 引入spring-cloud-config-server包以及服务发现包eureka-client，后续因为要结合Spring cloud bus则还需引入spring-cloud-starter-bus-amqp
```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
```
>* 相关配置：
```
        # 配置仓库
        spring.cloud.config.server.git.uri=https://github.com/liuzjccc/SpringCloud.git
        spring.cloud.config.server.git.search-paths=config-repo
        spring.cloud.config.server.git.username=liuzjccc
        spring.cloud.config.server.git.password=79882644lzj
        # Spring2.0改动配置（暴露 POST: localhost:xxxx/actuator/bus-refresh 接口（当调用此接口后，所有连接了配置中心的所有服务都会刷新配置缓存即重新拉取最新配置））
        management.endpoints.web.exposure.include= bus-refresh
```
>* 在启动类上添加注解：@EnableConfigServer
```
        @EnableConfigServer // 配置中心
        @EnableEurekaClient // 服务发现（被别人发现以及发现别人）
        @SpringBootApplication
        public class ConfigserverApplication {
        
            public static void main(String[] args) {
                SpringApplication.run(ConfigserverApplication.class, args);
            }
        }
```
>* 配置服务器和此服务启动后。如果GIT中的配置文件改动了，可以调用localhost:8767/actuator/bus-refresh通知连接到了配置中心的各个服务器去获取新的配置文件并刷新到缓存
![Image text](https://github.com/liuzjccc/SpringCloud/raw/master/images/springbus-configserver.png)

