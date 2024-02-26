package com.microservices.user.service.UserService.services;

import com.microservices.user.service.UserService.entities.Rating;
import com.microservices.user.service.UserService.entities.User;

import java.util.List;

public interface UserService{

    // create

    User saveUser(User user);

    // update
    User updateUser(User user, String userId);

    // get all users

    List<User> getUsers();

    // delete

    void deleteUser();

    // get by Id

    User getUserById(String userId);


    // create Rating

    Rating createRating(Rating rating);




}
