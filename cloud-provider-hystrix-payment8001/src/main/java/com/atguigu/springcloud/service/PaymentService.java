package com.atguigu.springcloud.service;


import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {

    String paymentInfo_OK(Integer id);

    String paymentInfo_Timeout(Integer id);

    public String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
