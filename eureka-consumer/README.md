# SprinCloud
### 此服务实现断路器，读取仓库配置（配置client）
#### 一.断路器-仪表盘实现步骤
>* 引入spring-cloud-starter-netflix-eureka-client（服务发现基础包）、com.netflix.hystrix和spring-boot-starter-actuator（断路器和被仪盘监控需要的包）
```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
            <version>1.4.5.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>com.netflix.hystrix</groupId>
            <artifactId>hystrix-javanica</artifactId>
        </dependency>
```
>* 在启动类上加上：@EnableCircuitBreaker 以及注入HystrixMetricsStreamServlet（第三方的servlet），该servlet是hystrix的组件
```
        @Bean
        public ServletRegistrationBean getServlet() {
            HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
            ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
            registrationBean.setLoadOnStartup(1);
            registrationBean.addUrlMappings("/hystrix.stream");
            registrationBean.setName("HystrixMetricsStreamServlet");
            return registrationBean;
        }
```
>* 启动之后在浏览器中输入localhost:8763/hystrix.stream看是否可被监控，否则无法在仪盘看到输出

#### 二.配置读取以及Spring cloud bus实现步骤
>* 引入spring-cloud-starter-config(配置读取基础包)、spring-cloud-starter-bus-amqp（实现spring cloud bus）
```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
```
>* 添加bootstarp配置文件，必须有此配置文件（配置配置服务器以及读取的配置文件）
```
        #开启配置服务发现
        spring.cloud.config.discovery.enabled=true
        #配置服务实例名称
        spring.cloud.config.discovery.service-id=config-server
        #配置文件所在分支
        spring.cloud.config.label=master
        spring.cloud.config.profile=dev
        #配置服务中心（如果没有配置第1、2项则直接配置此项，即配置中心没有注册到服务中心时就直接配置配置中心的uri）
        #spring.cloud.config.uri=http://localhost:8767/
        #此行配置一定要放在此文件中而不能放到application.properties中否则会报错
        eureka.client.serviceUrl.defaultZone= http://localhost:8761/eureka/
```
>* 配置服务器和此服务启动后，此服务可以通过@value方式读取远程GIT中的配置文件。如果GIT中的配置文件改动了，可以调用localhost:8767/actuator/bus-refresh通知连接到了配置中心的各个服务器去获取新的配置文件并刷新到缓存
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
        #zipkin链路分析
        spring.zipkin.locator.discovery.enabled= true
        spring.zipkin.base-url= http://localhost:8799
        spring.zipkin.discovery-client-enabled=true
        spring.zipkin.sender.type=web
        spring.sleuth.sampler.probability=1.0
```