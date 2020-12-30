package com.marcuschiu.resource.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/foos")
public class FooController {

    List<String> foos = new ArrayList<>();

    @CrossOrigin(origins = "http://localhost:8089")
    @GetMapping(value = "/{id}")
    public String findOne(@PathVariable Integer id) {
        return foos.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody String newFoo) {
        foos.add(newFoo);
    }

    @GetMapping
    public Collection<String> findAll() {
        return foos;
    }

    @PutMapping("/{id}")
    public String updateFoo(@PathVariable("id") Integer id, @RequestBody String updatedFoo) {
        return foos.set(id, updatedFoo);
    }
}