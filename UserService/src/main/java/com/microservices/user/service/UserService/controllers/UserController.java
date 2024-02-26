package com.microservices.user.service.UserService.controllers;


import com.microservices.user.service.UserService.entities.Rating;
import com.microservices.user.service.UserService.entities.User;
import com.microservices.user.service.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);


    // POST

    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user){

        User newUser = userService.saveUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    // get all

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }


    // get By Id


    int retryCount = 1;
    @GetMapping("/{userId}")
//  @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//  @Retry(name="ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userId){

        logger.info("Retry Count: {}", retryCount);
        retryCount++;
        User user = userService.getUserById(userId);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }


    //creating fallback method for circuit breaker

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
    logger.info("Fallback is executed because system is down :", ex.getMessage());
    User user= User.builder().email("dummmy@email.com").about("Dummy User because service is down").name("Dummy").userId("12345").build();

    return new ResponseEntity<User>(user,HttpStatus.OK);



    }


    // POST  RATING

    @PostMapping("/rating/save")
    public  ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating savedRating = userService.createRating(rating);
        return  new ResponseEntity<>(savedRating,HttpStatus.CREATED);
    }

}
