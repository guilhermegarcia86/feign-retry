package com.example.demo.client;

import com.example.demo.dto.response.Cep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cepClient", url = "${externalUrl}")
public interface CepClient {

    @GetMapping(value = "/v1/cep/{cep}")
    Cep get(@PathVariable("cep") String cep);
}
