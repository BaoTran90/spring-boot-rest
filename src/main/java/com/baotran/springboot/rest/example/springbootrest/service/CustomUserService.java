package com.baotran.springboot.rest.example.springbootrest.service;

import com.baotran.springboot.rest.example.springbootrest.model.CustomUser;
import com.baotran.springboot.rest.example.springbootrest.model.UserEntity;
import com.baotran.springboot.rest.example.springbootrest.repository.OAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    OAuthRepository oAuthRepository;

    @Override
    public CustomUser loadUserByUsername(final String username) {
        UserEntity userEntity;
        try {
            userEntity = oAuthRepository.getUserDetails(username);
            CustomUser customUser = new CustomUser(userEntity);
            return customUser;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }
}
