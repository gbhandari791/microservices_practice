package com.hotel.service.services;

import com.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    Hotel getHotel(String id);

    List<Hotel> getAllHotel();

    Hotel updateHotel(String id, Hotel hotel);

    void deleteHotel(String id);
}
