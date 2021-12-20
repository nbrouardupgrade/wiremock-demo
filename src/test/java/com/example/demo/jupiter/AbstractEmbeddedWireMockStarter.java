package com.example.demo.jupiter;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Jupiter junit test with WireMock Extension.
 * <p/>
 * If not using Jupiter, we can use the class
 * <a href="https://github.com/Credify/credit-decision-srvc/blob/master/credit-decisioning-server/src/test/java/com/credify/creditds/starter/AbstractEmbeddedWiremockStarter.java#L30-L30">AbstractEmbeddedWiremockStarter</a>.
 */
@SpringBootTest
public abstract class AbstractEmbeddedWireMockStarter {

    @RegisterExtension
    static WireMockExtension wiremock = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(9999) // TODO I don't know how to get the dynamic port and set the FeignClient with it
                    .extensions(new ResponseTemplateTransformer(true))
                    .usingFilesUnderClasspath("src/test/resources/wiremock/"))
            .build();
}
