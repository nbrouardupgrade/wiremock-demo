package com.example.demo.junit4;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.DemoClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = "demo.server.port=${wiremock.server.port}")
public class WireMockDemoTests {

    @Autowired
    private DemoClient demoClient;

    @Test
    public void test1() {
        stubFor(get("/public/v1/users")
                .willReturn(ok("body")));
        final var call = demoClient.hello();
        assertThat(call).isEqualTo("body");
    }
}
