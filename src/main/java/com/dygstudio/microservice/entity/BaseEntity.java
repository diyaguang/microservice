package com.dygstudio.microservice.entity;

public abstract class BaseEntity<T> extends Entity<T> {

    public BaseEntity(T id,String name){
        super.id = id;
        super.name = name;
    }
}
