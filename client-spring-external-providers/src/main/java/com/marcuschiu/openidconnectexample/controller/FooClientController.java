package com.marcuschiu.openidconnectexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class FooClientController {

    @Value("${resource-server.api.url}")
    String fooApiUrl;

    @Autowired
    WebClient webClient;

    @GetMapping("/foos")
    public List<String> getFoos() {
        List<String> foos = this.webClient.get()
            .uri(fooApiUrl)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
            .block();
        return foos;
    }
}
