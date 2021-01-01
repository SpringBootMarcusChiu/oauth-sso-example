package com.marcuschiu.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/foos")
public class FooController {

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
    public void create(@RequestBody String newFoo) {
        foos.add(newFoo);
    }

}