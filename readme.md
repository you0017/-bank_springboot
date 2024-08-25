# 生产端:

1.jms+activemq 消息部分

2.异步编程: 线程池+异步调用

    spring: 
        #线程池
        task:
        execution:
        pool:
        core-size: 5            # 核心线程数
        max-size: 10            # 最大线程数
        queue-capacity: 100     # 队列容量
        keep-alive: 60s         # 空闲线程存活时间
        thread-name-prefix: "AsyncThread-" #线程名
        @EnableAsync
        @Async("asyncExecutor")//参数指定线程名


3.启动druid数据库连接池

4.启用mybatis-plus

5.业务层用aop机制

    <!--aop-->
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
    @使用@Aspect注解定义切面等
6.测试框架junit4，测试套件


# 消费端:

1.使用jms的消费端读取消息

2.使用velocity读取邮件模板，并从消息中取出数据，并渲染到模板中

3.使用javax.mail发送邮件

4.发送邮件时使用异步任务

5.提供一个数据中台页面，显示实时发送情况，websocket

    发送量
    发送方和接收方地址