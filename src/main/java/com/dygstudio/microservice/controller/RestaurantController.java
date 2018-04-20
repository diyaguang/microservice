package com.dygstudio.microservice.controller;

import com.dygstudio.microservice.entity.Entity;
import com.dygstudio.microservice.entity.Restaurant;
import com.dygstudio.microservice.service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {
    protected Logger logger = Logger.getLogger(RestaurantController.class.getName());
    protected RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Restaurant>> findByName(@RequestParam("name") String name){
        logger.info(String.format("restaurant-service findByName() invoked:{} for {} ",restaurantService.getClass().getName(),name));
        name = name.trim().toLowerCase();
        Collection<Restaurant> restaurants;
        try{
            restaurants = restaurantService.findByName(name);
        }catch (Exception ex){
            logger.log(Level.WARNING,"Exception raised findByName REST Call",ex);
            return new ResponseEntity<Collection<Restaurant>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurants.size()>0?new ResponseEntity<Collection<Restaurant>>(restaurants, HttpStatus.OK):new ResponseEntity<Collection<Restaurant>>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{restaurant_id}",method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(@PathVariable("restaurant_id") String id){
        logger.info(String.format("restaurant-service findById() invoked:{} for {} ",restaurantService.getClass().getName(),id));
        id=id.trim();
        Entity restaurant;
        try{
            restaurant = restaurantService.findById(id);
        }catch (Exception ex){
            return new ResponseEntity<Entity>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurant!=null?new ResponseEntity<Entity>(restaurant,HttpStatus.OK):new ResponseEntity<Entity>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Restaurant> add(@RequestBody Restaurant restaurantV0) {
        logger.info(String.format("restaurant-service add() invoked: %s for %s", restaurantService.getClass().getName(), restaurantV0.getName()));
        Restaurant restaurant = new Restaurant(null, null, null);
        BeanUtils.copyProperties(restaurantV0, restaurant);
        try {
            restaurantService.add(restaurant);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Restaurant REST Call " + ex);
            return new ResponseEntity<Restaurant>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<Restaurant>(HttpStatus.CREATED);
    }
}
