package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/23
 * @Description:
 */
@SpringBootApplication
@EnableHystrixDashboard  //开启Hystrix监控视图工具，同时被监控服务需要有actuator和starter-web依赖
/*
例如监控8001->
地址http://localhost:8001/hystrix.stream
Delay:2000
Title:T3
 */
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class,args);
    }
}
