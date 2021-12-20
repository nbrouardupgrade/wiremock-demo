package com.example.demo.junit4;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.DemoClient;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WireMockDemo2Tests extends AbstractEmbeddedWireMockStarter {
    @Autowired
    private DemoClient demoClient;

    @Test
    public void test1() {
        stubFor(get("/public/v1/users")
                .willReturn(ok("body")));
        final var call = demoClient.hello();
        assertThat(call).isEqualTo("body");
    }

    @Test
    public void test2() {
        stubFor(get("/public/v1/users")
                .willReturn(ok().withBodyFile("response2.json")));
        final var call = demoClient.hello();
        assertThat(call).contains("Hello World!");
    }

    @Test
    public void test3() {
        stubFor(get("/public/v1/users")
                .willReturn(ok().withBody("{{request.url}}")));
        final var call = demoClient.hello();
        assertThat(call).contains("/public/v1/users");
    }

    @Test
    public void test4() {
        stubFor(get("/public/v1/users")
                .willReturn(ok().withBodyFile("response3.json")
                        .withTransformerParameter("key", "Upgrade")));
        final var call = demoClient.hello();
        assertThat(call).contains("Hello Upgrade!");
    }
}
