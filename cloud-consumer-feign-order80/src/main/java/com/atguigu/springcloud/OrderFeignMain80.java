package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/21
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients  //激活OpenFeign

//具体实现：
//跳转controller找到服务名下的对应方法->此处找到8001对外暴露的对应的方法payment/get/{id}
//controller接口调用service接口，service接口的GetMapping再调用客户端对外暴露的对应方法
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class,args);
    }
}
