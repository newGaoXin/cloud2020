package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigInfoController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(path = "/configInfo")
    public String getConfigInfo() {
        return "serverPort：" + serverPort + "\t\n\n" + "configInfo：" + configInfo;
    }
}
