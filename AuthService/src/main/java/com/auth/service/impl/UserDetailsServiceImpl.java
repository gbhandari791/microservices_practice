package com.auth.service.impl;

import com.auth.entity.User;
import com.auth.entity.UserDetailsImpl;
import com.auth.exception.ResourceNotFoundException;
import com.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username)
                .orElseThrow( () -> new ResourceNotFoundException("User", "email", username));


        return new UserDetailsImpl(user);
    }
}
