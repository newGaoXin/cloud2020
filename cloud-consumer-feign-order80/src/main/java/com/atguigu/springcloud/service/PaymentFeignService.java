package com.atguigu.springcloud.service;

import com.atguigu.springcloud.commons.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @RequestMapping(path = "/payment/get/{id}")
    public Result getPaymentById(@PathVariable("id") long id);

    @GetMapping(path = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
