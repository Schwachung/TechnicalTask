package com.example.propertyview.controller;

import com.example.propertyview.model.Hotel;
import com.example.propertyview.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return hotelService.findById(id);
    }

    @GetMapping("/search")
    public List<Hotel> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenity
    ) {
        return hotelService.search(name, brand, city, country, amenity);
    }

    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelService.save(hotel);
    }

    @PostMapping("/{id}/amenities")
    public void addAmenities(@PathVariable Long id,
                             @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
    }

    @GetMapping("/histogram/{param}")
    public Map<String, Long> getHistogram(@PathVariable String param) {
    return hotelService.histogram(param);
    }
}