package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/25
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient //作为微服务注册进eureka
//gateway不能有starter-web依赖
//git提交测试01
public class GatewayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9527.class,args);
    }
}
