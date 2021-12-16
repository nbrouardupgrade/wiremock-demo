package com.example.demo;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class WireMockDemo2Tests extends AbstractEmbeddedWireMockStarter {
    @Autowired
    private DemoClient demoClient;

    @Test
    void test1() {
        wiremock.stubFor(get("/public/v1/users")
                .willReturn(ok("body")));
        final var call = demoClient.hello();
        assertThat(call).isEqualTo("body");
    }

    @Test
    void test2() {
        wiremock.stubFor(get("/public/v1/users")
                .willReturn(ok().withBodyFile("response2.json")));
        final var call = demoClient.hello();
        assertThat(call).contains("Hello World!");
    }

    @Test
    void test3() {
        wiremock.stubFor(get("/public/v1/users")
                .willReturn(ok().withBody("{{request.url}}")));
        final var call = demoClient.hello();
        assertThat(call).contains("/public/v1/users");
    }

    @Test
    void test4() {
        wiremock.stubFor(get("/public/v1/users")
                .willReturn(ok().withBodyFile("response3.json")
                        .withTransformerParameter("key", "Upgrade")));
        final var call = demoClient.hello();
        assertThat(call).contains("Hello Upgrade!");
    }
}
