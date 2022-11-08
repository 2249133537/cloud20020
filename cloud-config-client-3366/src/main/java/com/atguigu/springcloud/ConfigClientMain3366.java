package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: 杨璨玮
 * @Date: 2022/11/8
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
/*
未加RabbitMQ之前有Git更改信息之后，只有服务端接收到修改，客户端需要重启才接收到修改的问题
 */
public class ConfigClientMain3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3366.class,args);
    }
}
