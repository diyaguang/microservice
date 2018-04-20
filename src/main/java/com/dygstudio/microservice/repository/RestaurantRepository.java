package com.dygstudio.microservice.repository;

public interface RestaurantRepository<Restaurant,String> extends Repository<Restaurant,String>{
    boolean ContainsName(String name);
}
