package com.microservices.hotel.services.HotelServices.controllers;

import com.microservices.hotel.services.HotelServices.entities.Hotel;
import com.microservices.hotel.services.HotelServices.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // POST

    @PostMapping("/save")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){

        Hotel newHotel = hotelService.save(hotel);
        return new ResponseEntity<>(newHotel, HttpStatus.CREATED);

    }

    // GET ALL

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotels(){

        List<Hotel> hotels = hotelService.getHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);

    }


    // GET by Id

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id){

        Hotel hotel = hotelService.getHotelById(id);
        return new ResponseEntity<>(hotel, HttpStatus.OK);

    }




}
