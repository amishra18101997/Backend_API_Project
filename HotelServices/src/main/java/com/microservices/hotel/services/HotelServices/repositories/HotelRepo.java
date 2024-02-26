package com.microservices.hotel.services.HotelServices.repositories;

import com.microservices.hotel.services.HotelServices.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel, String> {
}
