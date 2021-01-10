package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.commons.Result;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    private static final String serverUrl = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback") //没有配置
//    @SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") //blockHandler只负责sentinel控制
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",exceptionsToIgnore = {IllegalArgumentException.class})
    public Result<Payment> fallback(@PathVariable("id") Long id) {
        Result<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSQL/" + id, Result.class);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return result;
    }
    // 本例是fallback
    public Result handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new Result<>(444, "兜底异常handlerFallback,exception内容  " + e.getMessage(), payment);
    }
    //blockHandler
    public Result blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new Result<>(445, "blockHandler-sentinel限流,无此流水: blockException  " + blockException.getMessage(), payment);
    }

    //OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public Result<Payment> paymentSQL(@PathVariable("id") Long id){
        Result<Payment> result = paymentService.paymentSQL(id);
        return result;
    }
}
