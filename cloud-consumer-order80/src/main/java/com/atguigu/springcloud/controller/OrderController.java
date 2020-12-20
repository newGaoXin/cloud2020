package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.commons.CM;
import com.atguigu.springcloud.commons.Code;
import com.atguigu.springcloud.commons.RT;
import com.atguigu.springcloud.commons.Result;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //    private static final String url = "http://127.0.0.1:8001";
    private static final String url = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(path = "/consumer/payment/save", method = RequestMethod.POST)
    public Result save(Payment payment) {
        log.info("调用--- /payment/save");
        return restTemplate.postForObject(url + "/payment/save", payment, Result.class);
    }

    @RequestMapping(path = "/consumer/payment/get/{id}", method = RequestMethod.GET)
    public Result getPaymentById(@PathVariable("id") Long id) {
        log.info("调用--- /payment/get/{id}");
        return restTemplate.getForObject(url + "/payment/get/" + id, Result.class);
    }

    @RequestMapping(path = "/consumer/payment/getForEntity/save", method = RequestMethod.POST)
    public Result getForEntitySave(Payment payment) {
        log.info("调用--- /payment/save");
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity(url + "/payment/save", payment, Result.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            return new Result(Code.ERROR, "操作错误!");
        }
    }

    @RequestMapping(path = "/consumer/payment/getForEntity/{id}", method = RequestMethod.GET)
    public Result getForEntityPaymentById(@PathVariable("id") Long id) {
        ResponseEntity<Result> responseEntity = restTemplate.getForEntity(url + "/payment/get/" + id, Result.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            return new Result(444, "操作失败！");
        }
    }

    @RequestMapping(path = "/consumer/payment/lb", method = RequestMethod.GET)
    public String getPaymentLB() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance serviceInstance = loadBalancer.instance(serviceInstances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
