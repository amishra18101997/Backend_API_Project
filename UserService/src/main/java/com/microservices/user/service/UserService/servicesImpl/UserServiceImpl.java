package com.microservices.user.service.UserService.servicesImpl;

import com.microservices.user.service.UserService.entities.Hotel;
import com.microservices.user.service.UserService.entities.Rating;
import com.microservices.user.service.UserService.entities.User;
import com.microservices.user.service.UserService.exception.ResourceNotFoundException;
import com.microservices.user.service.UserService.external.services.HotelService;
import com.microservices.user.service.UserService.external.services.RatingService;
import com.microservices.user.service.UserService.repository.UserRepo;
import com.microservices.user.service.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Override
    public User saveUser(User user) {

        //generate unique userId
        String randomUserid = UUID.randomUUID().toString();
        user.setUserId(randomUserid);
        User newUser = this.userRepo.save(user);
        return newUser;
    }

    @Override
    public List<User> getUsers() {


        List<User> userList = this.userRepo.findAll();

        List<User> userList1 = userList.stream().map(singleUser -> {

            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + singleUser.getUserId(), Rating[].class);
            List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

            List<Rating> ratingList = ratings.stream().map(rating -> {

                //api call to HOTEL-SERVICE to get the hotel

                    //Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);

                    Hotel hotel = hotelService.getHotel(rating.getHotelId());

                //set the hotel to rating

                rating.setHotel(hotel);

                //return rating

                return rating;

            }).collect(Collectors.toList());


            singleUser.setRatings(ratingList);
            return singleUser;

        }).collect(Collectors.toList());


        return userList1;

    }

    @Override
    public User getUserById(String userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with given Id!!"));

       // Fetch rating of above user from RATING-SERVICE

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + user.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {

            //api call to HOTEL-SERVICE to get the hotel

                //Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);

                Hotel hotel = hotelService.getHotel(rating.getHotelId());

            //set the hotel to rating

            rating.setHotel(hotel);

            //return rating

            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }

    @Override
    public Rating createRating(Rating rating) {
        return ratingService.createRating(rating);
    }

    @Override
    public User updateUser(User user, String userId) {
        return null;
    }

    @Override
    public void deleteUser() {

    }
}
