package com.dygstudio.microservice.repository;

import com.dygstudio.microservice.entity.Entity;

import java.util.Collection;

public interface ReadOnlyRepository<TE,T> {
    boolean contains(T id);
    Entity get(T id);
    Collection<TE> getAll();
}
