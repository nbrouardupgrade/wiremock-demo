package com.example.demo;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WireMockDemo2Tests {
    @Autowired
    private DemoClient demoClient;

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(9999)
                    .extensions(new ResponseTemplateTransformer(true)))
            .build();

    @Test
    void test1() {
        wm.stubFor(get("/public/v1/users")
                .willReturn(ok("body")));
        final var call = demoClient.hello();
        assertThat(call).isEqualTo("body");
    }

    @Test
    void test2() {
        wm.stubFor(get("/public/v1/users")
                .willReturn(ok().withBodyFile("response2.json")));
        final var call = demoClient.hello();
        assertThat(call).contains("Hello World!");
    }

    @Test
    void test3() {
        wm.stubFor(get("/public/v1/users")
                .willReturn(ok().withBody("{{request.url}}")));
        final var call = demoClient.hello();
        assertThat(call).contains("/public/v1/users");
    }

    @Test
    void test4() {
        wm.stubFor(get("/public/v1/users")
                .willReturn(ok().withBodyFile("response3.json")
                        .withTransformerParameter("key", "Upgrade")));
        final var call = demoClient.hello();
        assertThat(call).contains("Hello Upgrade!");
    }
}
