package com.microservices.rating.service.RatingService.servicesImpl;

import com.microservices.rating.service.RatingService.entities.Rating;
import com.microservices.rating.service.RatingService.repositories.RatingRepo;
import com.microservices.rating.service.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {


    @Autowired
    private RatingRepo ratingRepo;

    @Override
    public Rating saveRating(Rating rating) {

        String randomRatingId = UUID.randomUUID().toString();
        rating.setRatingId(randomRatingId);
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUser(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotel(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
