package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import feign.Headers;
import shaw.spectrum.system.LoginDTO;
import shaw.spectrum.system.resource.LoginResource;

/**
 * Feign login API interface for Spectrum.
 *
 * @author ybelanger
 */
@FeignClient(name = "spectrumLogin", url = "${spectrum.server.scheme:http}://${spectrum.server.host}:${spectrum.server.port}")
public interface SpectrumLoginApi {
    @PostMapping("/spectrum/rws/pub/users/loginstatus")
    @Headers("Content-Type: application/json")
    LoginResource login(LoginDTO loginDTO);
}
