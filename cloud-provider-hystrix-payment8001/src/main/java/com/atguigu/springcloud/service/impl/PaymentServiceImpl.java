package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        String threadName = Thread.currentThread().getName();
        return "线程池：" + threadName + "paymentInfo_OK,id: " + id;
    }


    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
        Integer timeout  = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String threadName = Thread.currentThread().getName();

        return "线程池：" + threadName + "paymentInfo_Timeout,id: " + id + "\t" + "耗时（秒）：" + timeout;
    }
    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + " 服务请求超时或者运行异常报错,id: " + id ;
    }
}
