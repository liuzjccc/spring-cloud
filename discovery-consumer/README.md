# SprinCloud
##### 此服务使用DiscoveryClient调用服务，RabbitMQ的简单实现
###一.DiscoveryClient配置步骤
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
```
>* 在启动类上加上@EnableDiscoveryClient、@EnableFeignClients两个注解
>* 创建FeignClient服务接口调用类
```
        @FeignClient(value = "SERVICE-HI")
        public interface TestService {
            @RequestMapping(value = "hi", method = RequestMethod.GET)
            String hiService(@RequestParam String name);
        }
```
###1.RabbitMQ的配置步骤
>* 引入spring-boot-starter-amqp包
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
```
>* 在配置文件中连接RabbitMQ,以及创建MQ的基础Bean配置文件
```
        #RabbitMQ
        spring.rabbitmq.host=localhost
        spring.rabbitmq.port=5672
        spring.rabbitmq.username=xxx
        spring.rabbitmq.password=***
```
```
        @Configuration
        public class RabbitMQConfig {
            @Bean
            public Queue helloQueue() {
                return new Queue("hello");
            }
        }
```
>* 创建消息生成者和消费者
```
        @Component
        @RabbitListener(queues = "hello")
        public class InfoReceiver {
            @RabbitHandler
            public void process(String msg) {
                System.out.println("Receiver:"+msg);
            }
        }
```
```
        @Component
        public class InfoSender {
            @Autowired
            private AmqpTemplate rabbitTemplate;
            public void send(String info) {
                String msg = info + ": " + new Date();
                System.out.println("Sender:"+msg);
                this.rabbitTemplate.convertAndSend("hello", msg);
            }
        }
```
>* 测试
