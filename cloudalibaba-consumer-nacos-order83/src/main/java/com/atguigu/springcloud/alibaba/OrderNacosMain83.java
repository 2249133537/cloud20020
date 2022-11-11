package com.atguigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: HeChi
 * @Date: 2022/11/11
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient  //将微服务注册进nacos
/*
当前服务我们要通过Ribbon调用
Ribbon调用步骤
一个消费端，多个服务端情况下
消费端要配置Ribbon相关pom(这里Nacos中自带)
配置config的RestTemplate
再controller或Yml中配置并获取服务端的服务地址
注入RestTemplate
 */
public class OrderNacosMain83 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain83.class,args);
    }
}
