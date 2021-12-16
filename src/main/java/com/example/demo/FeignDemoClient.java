package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "demo", url = "${demo.server.scheme}://${demo.server.host}:${demo.server.port}")
public interface FeignDemoClient {
    @GetMapping("/public/v1/users")
    String getHello();
}
