package com.marcuschiu.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
public class FooClientController {

    @Value("${resourceserver.api.url}")
    String fooApiUrl;

    @Autowired
    WebClient webClient;

    @GetMapping("/foos")
    public String getFoos(Model model) {
        List<String> foos = this.webClient.get()
            .uri(fooApiUrl)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<String>>() {
            })
            .block();
        model.addAttribute("foos", foos);
        return "foos";
    }
}
