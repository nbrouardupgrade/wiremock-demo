package com.example.demo.junit4;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class SpectrumWireMock {
    DateTimeFormatter RESOURCE_CREATION_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy")
            .withLocale(Locale.US)
            .withZone(ZoneOffset.UTC);

    ObjectMapper objectMapper = new ObjectMapper();

    public void init() {
        login();
        customers();
    }

    private void login() {
        stubFor(post("/spectrum/rws/pub/users/loginstatus")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("login/loginstatus.json")
                        .withTransformerParameter("creationTime", RESOURCE_CREATION_TIME_FORMATTER.format(LocalDateTime.now()))
                ));
    }

    @SneakyThrows
    private String toJson(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    private void customers() {
        stubFor(post("/spectrum/rws/sc/customers/individualcustomers?withExist=true")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("customer/individualCustomer.json")));

        stubFor(get("/spectrum/rws/sc/customers/individualcustomers/")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("customer/individualCustomer.json")));
    }

    private void customers2() {
        stubFor(post("/spectrum/rws/sc/customers/individualcustomers?withExist=true")
                .willReturn(okJson("{{{request.body}}}")));
    }



}
