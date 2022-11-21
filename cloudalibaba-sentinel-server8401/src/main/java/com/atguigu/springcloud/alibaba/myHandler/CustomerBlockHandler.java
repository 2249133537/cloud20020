package com.atguigu.springcloud.alibaba.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;

/**
 * @Author: HeChi
 * @Date: 2022/11/21
 * @Description: 全局处理handler
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"按照用户自定义---global handlerException----01",new Payment(2020L,"serial003"));
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444,"按照用户自定义---global handlerException----02",new Payment(2020L,"serial003"));
    }
}
