package com.dygstudio.microservice.service;

import com.dygstudio.microservice.entity.Restaurant;
import com.dygstudio.microservice.repository.RestaurantRepository;

import java.math.BigInteger;
import java.util.Collection;

public class RestaurantService extends BaseService<Restaurant,BigInteger> {
    private RestaurantRepository<Restaurant,String> restaurantRepository;
    public RestaurantService(RestaurantRepository repository){
        super(repository);
        restaurantRepository = repository;
    }

    public void add(Restaurant restaurant) throws Exception{
        if(restaurantRepository.ContainsName(restaurant.getName())){
            throw new Exception(String.format("There is already a product with the name -%s",restaurant.getName()));
        }
        if(restaurant.getName()==null || "".equals(restaurant.getName())){
            throw new Exception("Restaurant name cannot be null or empty string.");
        }
        super.add(restaurant);
    }
    public Collection<Restaurant> findByName(String name){
        return restaurantRepository.getAll();
    }
}
