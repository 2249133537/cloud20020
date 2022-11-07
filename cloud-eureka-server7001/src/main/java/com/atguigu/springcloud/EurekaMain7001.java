package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/18
 * @Description:
 */
@SpringBootApplication
@EnableEurekaServer //注明这是服务注册中心
/*
通过localhost:7001进入单机版eureka中心
需要将服务(provider)注册进来，才能在eureka中心发现
再提供给消费者(Consumer)使用
 */
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class,args);
    }
}
