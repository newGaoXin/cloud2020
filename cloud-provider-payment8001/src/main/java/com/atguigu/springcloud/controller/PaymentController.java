package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.commons.RT;
import com.atguigu.springcloud.commons.Result;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(path = "/payment/save", method = RequestMethod.POST)
    public Result save(@RequestBody Payment payment) {
        int save = paymentService.save(payment);

        if (save > 0) {
            log.info("成功插入数据------  servicePort: " + servicePort);
            return RT.success(200, "成功插入数据------  servicePort: " + servicePort);
        }
        return RT.error();
    }

    @RequestMapping(path = "/payment/get/{id}")
    public Result getPaymentById(@PathVariable("id") long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return RT.error("数据不存在-----  servicePort:  " + servicePort);
        }
        return RT.success(200, "成功-----  servicePort:  " + servicePort, payment);
    }

    @RequestMapping(path = "/payment/discovery", method = RequestMethod.GET)
    public Object getDiscovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("-----" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @RequestMapping(path = "/payment/lb",method = RequestMethod.GET)
    public String getPaymentLB(){
        return servicePort;
    }

    @GetMapping(path = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return servicePort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
