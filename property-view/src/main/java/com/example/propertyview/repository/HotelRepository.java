package com.example.propertyview.repository;

import com.example.propertyview.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByNameContainingIgnoreCase(String name);

    List<Hotel> findByBrandIgnoreCase(String brand);

    List<Hotel> findByAddressCityIgnoreCase(String city);

    List<Hotel> findByAddressCountryIgnoreCase(String country);

    List<Hotel> findByAmenitiesContainingIgnoreCase(String amenity);
}