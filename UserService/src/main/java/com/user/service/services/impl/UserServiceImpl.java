package com.user.service.services.impl;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.feign.services.HotelService;
import com.user.service.feign.services.RatingService;
import com.user.service.repository.UserRepository;
import com.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;

    @Override
    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Fetch user ratings form Rating service
        List<Rating> userRatings = ratingService.getUserRatings(user.getUserId());

        // Fetch Hotel of rating
        userRatings = userRatings.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).toList();


        user.setRating(userRatings);

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User updateUser(String id, User user) {
        User dbUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        dbUser.setName(user.getName());
        dbUser.setEmail(user.getEmail());
        dbUser.setAbout(user.getAbout());
        return userRepository.save(dbUser);
    }

    @Override
    public void deleteUser(String id) {
        User dbUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(dbUser);
    }

    @Override
    public Rating createRating(Rating rating) {

        return ratingService.createRating(rating);
    }

    @Override
    public Rating updateRating(String ratingId, Rating rating) {
        return ratingService.updateRating(ratingId, rating);
    }

    @Override
    public void deleteRating(String ratingId) {
        ratingService.deleteRating(ratingId);
    }
}
