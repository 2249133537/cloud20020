package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: 杨璨玮
 * @Date: 2022/11/7
 * @Description:
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
//http://localhost:3344/master/config-dev.yml可访问gitHub对应文件内容->测试是否配置cloud-config成功
/*
配置完成RabbitMQ之后
curl -X POST "http://localhost:3344/actuator/bus-refresh" 刷新服务端，全局通知
curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355" 定点通知3355，3366不改变
所有客户端内容都会获得相应刷新(刷新过后内容和实际符合)
一次发送，处处生效
 */
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class,args);
    }
}
