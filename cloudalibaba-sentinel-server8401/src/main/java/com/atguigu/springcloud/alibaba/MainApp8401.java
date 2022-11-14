package com.atguigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: HeChi
 * @Date: 2022/11/14
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
/**
 * 要先启动nacos+sentinel
 * nacos   -> startup.cmd -m standalone
 * sentinel  ->java -jar +sentinel全名
 */
public class MainApp8401 {
    public static void main(String[] args) {
        SpringApplication.run(MainApp8401.class,args);
    }
}
