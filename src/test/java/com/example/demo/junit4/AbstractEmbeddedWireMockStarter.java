package com.example.demo.junit4;

import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * junit-4 test with WireMock Extension.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = "demo.server.port=${wiremock.server.port}")
@Import(AbstractEmbeddedWireMockStarter.EmbeddedWiremockConfig.class)
public abstract class AbstractEmbeddedWireMockStarter {

    /**
     * Config to automatically load wiremock mappings and files from a specific folder.
     */
    @Configuration
    static class EmbeddedWiremockConfig {

        @Bean
        public WireMockConfigurationCustomizer optionsCustomizer() {
            return options -> options.extensions(new ResponseTemplateTransformer(true))
                    .usingFilesUnderClasspath("src/test/resources/wiremock/");
        }
    }
}
