package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentService;
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
    public String paymentInfo_Timeout(Integer id) {
        Integer timeout  = 3;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String threadName = Thread.currentThread().getName();

        return "线程池：" + threadName + "paymentInfo_Timeout,id: " + id + "\t" + "耗时（秒）：" + timeout;
    }
}
