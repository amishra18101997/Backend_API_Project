package com.microservices.rating.service.RatingService.services;

import com.microservices.rating.service.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {


    // create

    Rating saveRating(Rating rating);


    // get All

    List<Rating> getAllRatings();


    // get All by UserId

    List<Rating> getRatingByUser(String userId);


    // get By HotelId

    List<Rating> getRatingByHotel(String hotelId);








}
