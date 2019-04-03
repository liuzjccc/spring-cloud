# SprinCloud
![Image](./images/springcloud.jpg)
#### 1.Spring Cloud Netflix
>>此包下有Netflix Eureka（服务注册，服务发现）、Netflix Hystrix（仪表盘，熔断器）、Netflix Zuul（动态路由，拦截器）、Netflix Archaius（配置管理API）
四个主要模块。目前项目实现了前三个实例。
>* eureka-server：此服务是EurekaServer，即服务注册中心
>* eureka-client：此服务是EurekaClient，即普通服务发现。服务发现可以使用EurekaClient,也可以使用DiscoveryClient。一般如果我们使用restTemplate + Ribbon服务调用则使用前者，而使用其他，例如Feign服务调用则使用后者
>* eureka-consumer：此服务是实现了断路器基于restTemplate + Ribbon服务调用的实例，此服务连接了配置中心configserver
>* eureka-consumer2：此服务是实现了断路器基于restTemplate + Ribbon服务调用的实例,此服务连接了配置中心configserver,此两个示例是验证配置中心利用消息总线实现所有服务的配置实时刷新，只需调用 [POST]:localhost:xxxx/actuator/bus-refresh 接口即可实现所有连接配置中心的服务配置刷新功能
>* discovery-consumer：此服务是基于DiscoveryClient服务调用的实例
>* zuul：此服务是实现了Zuul（网关）,动态路由到eurekaconsumer、eurekaconsumer2以及discoveryConsumer三个服务
>* hystrix-dashboard：此服务是实现了HystrixDashboard，即仪表盘服务
>>调用模型
>* 所有服务注册于服务中心
>* hystrix-dashboard监控
>* 各个服务之间可以互相调用
#### 2.Spring Cloud Config
>>将配置文件放到Git仓库中统一管理。配置信息的传输可以进行加密，但此实例没有加密处理
>* configserver：此服务充当配置中心服务器
>* eurekaconsumer & eurekaconsumer2：此服务的配置放到Git仓库中，配置命名为：application name + profile 例如：eurekaconsumer-dev.properties ;通过连接配置服务器configserver（连接的信息一定要写在bootstrap.properties文件中）获取对应的配置
#### 3.Spring Cloud Bus
>>消息总线：通知服务器某个事件发生，然后由服务器通知客户端。此处结合RabbitMQ使用，也可以结合Kafka（集成起来比RabbitMQ简单）。有一个坑就是2.0版本和之前版本有[改动的地方](https://www.cnblogs.com/lzj123/p/9724499.html),此实例支持手动通知，即敲连接：localhost:8767/actuator/bus-refresh。自动通知有待研究
>* discoveryConsumer: RabbitMQ简单使用
>* eurekaconsumer & eurekaconsumer2: 配置客户端
>* configserver: 配置服务端
##### ![Image](./images/springbus-configserver.png)
#### 4.Spring Cloud for Cloud Foundry
#### 5.Spring Cloud Cluster
#### 6.Spring Cloud Consul
