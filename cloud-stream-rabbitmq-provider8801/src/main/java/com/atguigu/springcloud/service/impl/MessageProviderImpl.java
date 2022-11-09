package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: HeChi
 * @Date: 2022/11/9
 * @Description: 开启输出源通道，通过output向消息中间件发送消息流水号，发布-订阅模式
 */
//此处不需要@Service注解，这并不是传统业务层，没有dao，没有数据库，而是操作消息中间件
//这个类会发送消息到RabbitMQ，其原因是通过POM文件的stream-rabbitmq整合
@EnableBinding(Source.class)  //生产者Source   定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {
    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {  //此处发出String类型的Message,8802就要收取String类型Message
        String serial = UUID.randomUUID().toString();
        //用消息构建器构建，并将自己的设定值存入其中，构建出生产者消息，这里还可以设置表头.setHeader("",value)
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("****serial:"+serial);
        return null;
    }
}
