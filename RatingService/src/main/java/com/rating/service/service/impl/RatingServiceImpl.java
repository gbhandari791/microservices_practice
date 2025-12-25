package com.rating.service.service.impl;

import com.rating.service.entity.Rating;
import com.rating.service.exceptions.ResourceNotFoundException;
import com.rating.service.repository.RatingRepository;
import com.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        rating.setId(UUID.randomUUID().toString());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUser(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotel(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public Rating updateRating(String ratingId, Rating rating) {
        Rating dbRating = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found with id: " + ratingId));
        dbRating.setRating(rating.getRating());
        dbRating.setFeedback(rating.getFeedback());
        return ratingRepository.save(dbRating);
    }

    @Override
    public void deleteRating(String ratingId) {
        Rating dbRating = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found with id: " + ratingId));
        ratingRepository.delete(dbRating);
    }
}
