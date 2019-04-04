# SprinCloud
##### 此服务使用EurekaClient调用服务，并实现路由控制（Zuul只是Spring cloud整合Netflix的，gateway才是Spring cloud自己的网关）
### 一.动态路由实现步骤
>* 引入包：spring-cloud-starter-zuul
```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zuul</artifactId>
            <version>1.4.5.RELEASE</version>
        </dependency>
```
>* 在配置文件中配置路由转发规则
```
        #路由配置，将api-a开头的转发到service-ribbon服务，api-b开头的转发到service-feign服务
        zuul.routes.api-a.path= /api-a/**
        zuul.routes.api-a.serviceId=service-ribbon
        zuul.routes.api-b.path= /api-b/**
        zuul.routes.api-b.serviceId=service-feign
```
>* 在启动类中添加：@EnableZuulProxy注解
![Image text](https://github.com/liuzjccc/SpringCloud/raw/master/images/zuul.png)
