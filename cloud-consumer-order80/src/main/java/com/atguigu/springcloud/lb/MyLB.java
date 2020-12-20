package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ?  0 : current + 1;
        } while (!atomicInteger.compareAndSet(current,next)); // 第一个参数是期望值，第二个参数是修改值

        System.out.println("******第几次访问，次数next ：" + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) { // 得到机器的列表
        int index = getAndIncrement() % serviceInstances.size(); // 得到服务器的下标位置
        return serviceInstances.get(index);
    }
}