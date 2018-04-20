package com.dygstudio.microservice.service;

import com.dygstudio.microservice.repository.Repository;

public abstract class ReadOnlyBaseService<TE,T> {
    private Repository<TE,T> repository;

    ReadOnlyBaseService(Repository<TE,T> repository){
        this.repository = repository;
    }
}
