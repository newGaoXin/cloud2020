package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.commons.RT;
import com.atguigu.springcloud.commons.Result;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

    @RequestMapping(path = "/payment/save",method = RequestMethod.POST)
    public Result save(@RequestBody Payment payment){
        int save = paymentService.save(payment);

        if (save > 0 ){
            log.info("成功插入数据------  servicePort: "+ servicePort);
            return RT.success(200,"成功插入数据------  servicePort: "+ servicePort);
        }
        return RT.error();
    }

    @RequestMapping(path = "/payment/get/{id}")
    public Result getPaymentById(@PathVariable("id") long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null){
            return RT.error("数据不存在-----  servicePort:  "+ servicePort);
        }
        return RT.success(200,"成功-----  servicePort:  "+ servicePort,payment);
    }

    @RequestMapping(path = "/payment/lb",method = RequestMethod.GET)
    public String getPaymentLB(){
        return servicePort;
    }
}
