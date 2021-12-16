package com.example.demo;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@WireMockTest(httpPort = 9999)
@SpringBootTest
class WireMockDemoTests {
    @Autowired
    private DemoClient demoClient;

    @Test
    void test1() {
        stubFor(get("/public/v1/users")
                .willReturn(ok("body")));
        final var call = demoClient.hello();
        assertThat(call).isEqualTo("body");
    }

    @Test
    void test2() {
        stubFor(get("/public/v1/users")
                .willReturn(ok().withBodyFile("response2.json")));
        final var call = demoClient.hello();
        assertThat(call).isNotBlank();
    }

    @Test
    void test3() {
        stubFor(get("/public/v1/users")
                .willReturn(ok().withBodyFile("response2.json")));
        final var call = demoClient.hello();
        assertThat(call).isNotBlank();
    }
}
