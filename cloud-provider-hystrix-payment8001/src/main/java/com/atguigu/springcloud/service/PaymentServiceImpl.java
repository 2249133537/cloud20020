package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/22
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService{
    /**
     * 正常访问
     * 方法含义: 返回访问id
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池名: "+Thread.currentThread().getName()+"paymentInfo_OK,id: "+id;
    }

    /**
     * 故意写一个会报错的方法
     * @param id
     * @return
     */
    @Override
    /**
     * 无论是消费者还是服务提供者都可以做服务降级
     * 业务类启用fallback服务降级
     * 要在主启动类上添加@EnableCircuitBreaker
     * 设置自身调用时间峰值，峰值内可以正常运行，超时则认为当前服务不可用。
     * 如果超过了峰值，需要有兜底方法处理。作服务降级fallback
     * 如果服务运行异常，也做服务降级
     */
    //如果paymentInfo_TimeOut出问题，则由paymentInfo_TimeOutHandler兜底
    @HystrixCommand(fallbackMethod ="paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
            //此处规定，5秒钟以内的处理时间为正常，不正常则启用paymentInfo_TimeOutHandler兜底
    })
    public String paymentInfo_TimeOut(Integer id) {
        //制造超时异常用于测试,feign默认超过一秒报错,如要更改则在Yml更改相应配置
        int timeNumber = 2;
        //制造运行异常用于测试
//        int age = 10/0;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池名: "+Thread.currentThread().getName()+"paymentInfo_TimeOut,id: "+id;
    }

    @Override
    public String paymentInfo_TimeOutHandler(Integer id){
        return "端口8001 ：线程池名: "+Thread.currentThread().getName()+"系统繁忙或运行报错,id: "+id+"   兜底啦";
    }

    //========服务熔断
    //发生多次错误之后，服务熔断，即使是正确访问，也不会有正确结果，此时进入熔断状态
    //在熔断状态下，稍等一段时间(默认5秒)，输入正确结果，得到正确返回，此时进入半开状态。如果错误，再进入断路状态
    //在半开状态下，输入基本是正确访问，再进去开启状态
    //熔断状态下，fallback降级将成为主逻辑,在半开状态下释放一次请求，如果返回正确，则将原来主逻辑恢复
    //具体配置如下
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            //在时间窗口期的时间内，你的10次请求有60%是失败的，就跳闸
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数,默认20。如不足20次，发生错误也不会打开
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期(时间范围)
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少次后跳闸,默认50%
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }
        //用hutool工具生成不带-的UUID
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }

    @Override
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍后重试,id："+id;
    }

}
