package com.microservices.hotel.services.HotelServices.services;

import com.microservices.hotel.services.HotelServices.entities.Hotel;
import com.microservices.hotel.services.HotelServices.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface HotelService {

    // create

    Hotel save(Hotel hotel);

    // get all

    List<Hotel> getHotels();

    // get By Id

    Hotel getHotelById(String id);



}
