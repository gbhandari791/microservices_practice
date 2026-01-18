package com.auth.service.impl;

import com.auth.entity.User;
import com.auth.exception.CustomException;
import com.auth.payload.UserDto;
import com.auth.repo.UserRepo;
import com.auth.service.UserService;
import com.auth.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        boolean present = userRepo.findByEmail(userDto.getEmail()).isPresent();
        if(present) {
            throw new CustomException(GeneralUtil.concat("User with email ", userDto.getEmail(), " already exists"));
        }
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(userDto.getName());
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        User savedUser = this.userRepo.save(user);

        return UserDto.builder().userId(savedUser.getUserId()).name(savedUser.getName()).email(savedUser.getEmail())
                .about(savedUser.getAbout()).build();
    }
}
