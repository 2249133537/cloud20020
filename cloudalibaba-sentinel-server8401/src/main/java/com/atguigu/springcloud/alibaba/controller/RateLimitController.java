package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.myHandler.CustomerBlockHandler;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: HeChi
 * @Date: 2022/11/21
 * @Description: SentinelResource配置
 */
@RestController
public class RateLimitController {
    //sentinel控制台中按资源名---@SentinelResource配置
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流",new Payment(2020L,"serial001"));
    }
    //此处提供了更为友好的报错自定义提示内容
    public CommonResult handleException(BlockException exception){
        //exception.getClass().getCanonicalName()会展示出异常提示类
        return new CommonResult(444,exception.getClass().getCanonicalName()+"/t服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    //sentinel控制台中按url--->@GetMapping配置
    public CommonResult byUrl(){
        //此处采用默认的报错信息
        return new CommonResult(200,"按照url限流测试",new Payment(2020L,"serial002"));
    }

    /*
    上面的降级会面临以下问题：
    1.系统默认，没有体现自己的业务要求
    2.依照现有条件，我们自定义的处理方法又和业务代码耦合在一起，不直观
    3.每个业务方法都添加一个降级方法，代码膨胀加剧
    4.全局统一的处理方法没有体现

    为了解决上述问题，我们提出全局的统一处理handler
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    //这里制定了XX类中的XX方法作为此接口的降级方法
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException2")
    //sentinel控制台中按url--->@GetMapping配置
    public CommonResult customerBlockHandler(){
        //此处采用默认的报错信息
        return new CommonResult(200,"按照用户自定义",new Payment(2020L,"serial003"));
    }
}
