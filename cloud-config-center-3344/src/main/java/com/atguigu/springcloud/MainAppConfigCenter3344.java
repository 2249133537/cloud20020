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
//http://localhost:3344/master/config-dev.yml可访问gitHub对应文件内容
public class MainAppConfigCenter3344 {
    public static void main(String[] args) {
        SpringApplication.run(MainAppConfigCenter3344.class,args);
    }
}
