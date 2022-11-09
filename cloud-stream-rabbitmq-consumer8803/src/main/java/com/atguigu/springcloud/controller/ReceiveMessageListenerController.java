package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author: HeChi
 * @Date: 2022/11/9
 * @Description:
 */
@Component
@EnableBinding(Sink.class)  //消费则Sink
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT) //作为输入源进行监听
    public void input(Message<String> message){  //因为8801发送是String类型Message，所以这里指定泛型String
        //.getPayload()与8801业务层的withPayload()呼应
        System.out.println("this is consumer1, message:"+message.getPayload());
        System.out.println("port:"+serverPort);
    }
}
