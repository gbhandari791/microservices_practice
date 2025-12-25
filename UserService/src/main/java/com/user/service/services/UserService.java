package com.user.service.services;

import com.user.service.entity.Rating;
import com.user.service.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(String id);

    List<User> getAllUsers();

    User updateUser(String userId, User user);

    void deleteUser(String userId);

    Rating createRating(Rating rating);

    Rating updateRating(String ratingId, Rating rating);

    void deleteRating(String ratingId);
}
