package com.example.propertyview.service;

import com.example.propertyview.model.Address;
import com.example.propertyview.exception.HotelNotFoundException;
import com.example.propertyview.model.Hotel;
import com.example.propertyview.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    public List<Hotel> search(String name,
                              String brand,
                              String city,
                              String country,
                              String amenity) {

        if (amenity != null)
            return hotelRepository.findByAmenitiesContainingIgnoreCase(amenity);

        if (city != null)
            return hotelRepository.findByAddressCityIgnoreCase(city);

        if (brand != null)
            return hotelRepository.findByBrandIgnoreCase(brand);

        if (country != null)
            return hotelRepository.findByAddressCountryIgnoreCase(country);

        if (name != null)
            return hotelRepository.findByNameContainingIgnoreCase(name);

        return hotelRepository.findAll();
    }

    public void addAmenities(Long id, List<String> amenities) {
        Hotel hotel = findById(id);
        hotel.getAmenities().addAll(amenities);
        hotelRepository.save(hotel);
    }

    public Map<String, Long> histogram(String param) {

    List<Hotel> hotels = hotelRepository.findAll();
    Map<String, Long> result = new HashMap<>();

    for (Hotel hotel : hotels) {

        String key = null;

        if ("brand".equalsIgnoreCase(param)) {
            key = hotel.getBrand();
        }

        if ("city".equalsIgnoreCase(param) && hotel.getAddress() != null) {
            key = hotel.getAddress().getCity();
        }

        if ("country".equalsIgnoreCase(param) && hotel.getAddress() != null) {
            key = hotel.getAddress().getCountry();
        }

        if ("amenities".equalsIgnoreCase(param)) {

            if (hotel.getAmenities() != null) {
                for (String amenity : hotel.getAmenities()) {
                    result.put(amenity, result.getOrDefault(amenity, 0L) + 1);
                }
            }

            continue; // важно!
        }

        if (key != null) {
            result.put(key, result.getOrDefault(key, 0L) + 1);
        }
    }

    return result;
    }
}