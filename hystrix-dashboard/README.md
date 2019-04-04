# SprinCloud
### 此服务作为服务监控仪表盘
#### 一.仪盘实现步骤
>* 引入包：spring-cloud-starter-hystrix-dashboard
>* 在启动类中添加：@EnableHystrixDashboard注解
>* 直接访问http://localhost:端口号/hystrix 则可以看到仪盘网页，只需将被监控的（此处是监控断路器发生的情况及demo服务）服务输入input框然后点击Monitor Stream按钮即可看到详情
