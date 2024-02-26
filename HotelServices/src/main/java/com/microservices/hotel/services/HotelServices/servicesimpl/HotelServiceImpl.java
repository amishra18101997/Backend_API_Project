package com.microservices.hotel.services.HotelServices.servicesimpl;

import com.microservices.hotel.services.HotelServices.entities.Hotel;
import com.microservices.hotel.services.HotelServices.exceptions.ResourceNotFoundException;
import com.microservices.hotel.services.HotelServices.repositories.HotelRepo;
import com.microservices.hotel.services.HotelServices.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRepo;
    @Override
    public Hotel save(Hotel hotel) {

        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        Hotel newHotel = hotelRepo.save(hotel);
        return newHotel;
    }

    @Override
    public List<Hotel> getHotels() {

        List<Hotel> hotels = hotelRepo.findAll();
        return hotels;
    }

    @Override
    public Hotel getHotelById(String id) {

        Hotel hotel = hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with given Id"));
        return hotel;
    }
}
