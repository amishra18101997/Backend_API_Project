package com.microservices.rating.service.RatingService.repositories;

import com.microservices.rating.service.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

public interface RatingRepo extends MongoRepository<Rating, String> {


    //custom finder method

    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);

}
