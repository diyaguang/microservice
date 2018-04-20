package com.dygstudio.microservice.repository;

import com.dygstudio.microservice.entity.Entity;
import com.dygstudio.microservice.entity.Restaurant;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemRestaurantRepository implements RestaurantRepository<Restaurant,String> {
    private Map<String,Restaurant> entities;
    public InMemRestaurantRepository(){
        entities = new HashMap<>();
    }
    @Override
    public boolean ContainsName(String name){
        return entities.containsKey(name);
    }
    @Override
    public void add(Restaurant entity){
        entities.put(entity.getName(),entity);
    }
    @Override
    public void remove(String id){
        if(entities.containsKey(id)){
            entities.remove(id);
        }
    }
    @Override
    public void update(Restaurant entity){
        if(entities.containsKey(entity.getName())){
            entities.put(entity.getName(),entity);
        }
    }
    @Override
    public boolean contains(String id){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public Entity get(String id){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public Collection<Restaurant> getAll(){
        return entities.values();
    }
}
