package com.marcuschiu.opaque.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/bars")
public class BarController {

    List<String> foos = Arrays.asList("Marcus Chiu", "Erina Chiu", "Anna Chiu", "Eric Chiu");

    @GetMapping(value = "/{id}")
    public String findOne(@PathVariable Integer id) {
        return foos.get(id);
    }

    @GetMapping
    public List<String> findAll() {
        return foos;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody String newBar) {
        foos.add(newBar);
    }
}