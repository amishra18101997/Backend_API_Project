package com.microservices.user.service.UserService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rating {

    private String ratingId;
    private String userId;

    private String hotelId;

    private Integer rating;

    private String feedback;

    private Hotel hotel;


}
