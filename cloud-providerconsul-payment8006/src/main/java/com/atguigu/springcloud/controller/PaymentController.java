package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;


    @RequestMapping(path = "/payment/consul",method = RequestMethod.GET)
    public String paymentConsul(){
        return "SpringCloud With Consul: " + serverPort +  "\t" + UUID.randomUUID().toString();
    }
}
