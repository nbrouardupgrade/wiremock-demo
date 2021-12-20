package com.example.demo;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class
DemoClient {
    private final FeignDemoClient feignDemoClient;
    public String hello() {
        log.info("Calling GET /hello");
        final var hello = feignDemoClient.getHello();
        log.info("Received: {}", hello);
        return hello;
    }
}
