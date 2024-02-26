package com.microservices.user.service.UserService.external.services;


import com.microservices.user.service.UserService.entities.Rating;
import jakarta.ws.rs.GET;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //POST

    @PostMapping("/rating/save")
    Rating createRating(@RequestBody Rating rating);
    


}
