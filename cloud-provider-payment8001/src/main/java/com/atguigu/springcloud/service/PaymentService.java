package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {

    int save(Payment payment);

    Payment getPaymentById(Long id);
}
