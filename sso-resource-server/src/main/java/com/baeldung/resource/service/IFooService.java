package com.baeldung.resource.service;

import java.util.Optional;

import com.baeldung.resource.persistence.model.Foo;

public interface IFooService {
    Optional<Foo> findById(Long id);

    Foo save(Foo foo);

    Iterable<Foo> findAll();

}
