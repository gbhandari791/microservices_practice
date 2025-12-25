package com.user.service.feign.services;

import com.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/rating/user/{ratingId}")
    List<Rating> getUserRatings(@PathVariable String ratingId);

    @PostMapping("/rating")
    Rating createRating(Rating rating);

    @PutMapping("/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("/{ratingId}")
    void deleteRating(String ratingId);
}
