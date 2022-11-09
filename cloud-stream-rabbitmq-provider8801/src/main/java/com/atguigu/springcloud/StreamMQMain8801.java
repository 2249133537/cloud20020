package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: HeChi
 * @Date: 2022/11/9
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
/*
当有多个消费者时候，存在重复消费的问题
我们要对其进行分组，来解决重复消费
RabbitMQ默认每一个服务处于不同的组
分组后，同组之间的服务，存在竞争关系，一个消费到，其余就不消费
不同组之间仍然是会重复消费

配置分组：在消费者服务端Yml配置group:xxx  同名为同一组

group如果进行了分组，那会有  持久化  的机制
含义为：
8801服务端在8802，8803已分组的两个服务关闭时候给RabbitMQ发送消息
8802和8803两个消费端在服务启动后仍然能获得之前8801发送的消息
 */
public class StreamMQMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8801.class,args);
    }
}
