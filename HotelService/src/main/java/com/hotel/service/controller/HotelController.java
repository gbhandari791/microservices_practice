package com.hotel.service.controller;

import com.hotel.service.entity.Hotel;
import com.hotel.service.services.HotelService;
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

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){

        Hotel rHotel = hotelService.createHotel(hotel);
        return new ResponseEntity<>(rHotel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String id){
        Hotel rHotel = hotelService.getHotel(id);
        return new ResponseEntity<>(rHotel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> rHotel = hotelService.getAllHotel();
        return new ResponseEntity<>(rHotel, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String id, @RequestBody Hotel hotel){
        Hotel rHotel = hotelService.updateHotel(id, hotel);
        return new ResponseEntity<>(rHotel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable String id){
        hotelService.deleteHotel(id);
        return ResponseEntity.ok().build();
    }
}
