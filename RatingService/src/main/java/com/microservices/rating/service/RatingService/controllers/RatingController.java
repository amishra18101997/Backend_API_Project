package com.microservices.rating.service.RatingService.controllers;


import com.microservices.rating.service.RatingService.entities.Rating;
import com.microservices.rating.service.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {



    @Autowired
    private RatingService ratingService;

    //POST


    @PostMapping("/save")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){

        Rating savedRating = ratingService.saveRating(rating);

        return new ResponseEntity<Rating>(savedRating,HttpStatus.CREATED);


    }


    // GET ALL

    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAllRating(){

        List<Rating> allRatings = ratingService.getAllRatings();

        return new ResponseEntity<List<Rating>>(allRatings,HttpStatus.OK);


    }




    // GET BY USER


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getByUser(@PathVariable String userId){

        List<Rating> allRatings = ratingService.getRatingByUser(userId);

        return new ResponseEntity<List<Rating>>(allRatings,HttpStatus.OK);


    }


    // GET BY HOTEL

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getByHotel(@PathVariable String hotelId){

        List<Rating> allRatings = ratingService.getRatingByHotel(hotelId);

        return new ResponseEntity<List<Rating>>(allRatings,HttpStatus.OK);


    }



}
