package com.dygstudio.microservice.controller;

import com.dygstudio.microservice.entity.Restaurant;
import com.dygstudio.microservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
