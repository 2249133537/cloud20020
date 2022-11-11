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
@EnableDiscoveryClient  //nacos注册服务
/*
此类为nacos负载均衡使用范例
与9001一同启动测试负载均衡
 */
public class PaymentMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9002.class,args);
    }
}
