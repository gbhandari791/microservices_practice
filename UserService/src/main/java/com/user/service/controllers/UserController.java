package com.user.service.controllers;

import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User rUser = userService.createUser(user);
        return new ResponseEntity<>(rUser, HttpStatus.CREATED);
    }

    int count = 1;
    @GetMapping("/{userId}")
    // @CircuitBreaker(name = "ratingHotelCB", fallbackMethod = "getUserFallback")
    //@Retry(name = "ratingHotelCB", fallbackMethod = "getUserFallback")
    //@RateLimiter(name = "ratingHotelRL", fallbackMethod = "getUserFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){

        System.out.println("Calling service user service: " + count++);
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> getUserFallback(String userId, Exception e){
        System.out.println("Rating or Hotel fallback method is called");
        User user = User.builder().name("Dummy User")
                .email("dummy@gmail.com")
                .about("The user service is down")
                .userId("dummy123").build();
        return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId){
        User rUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(rUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1 = userService.createRating(rating);
        return new ResponseEntity<>(rating1, HttpStatus.CREATED);
    }

    @PutMapping("/rating/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody Rating rating){
        Rating rating1 = userService.updateRating(ratingId, rating);
        return new ResponseEntity<>(rating1, HttpStatus.OK);
    }

    @DeleteMapping("/rating/{ratingId}")
    public ResponseEntity<Void> updateRating(@PathVariable String ratingId){
        userService.deleteRating(ratingId);
        return ResponseEntity.ok().build();
    }
}
