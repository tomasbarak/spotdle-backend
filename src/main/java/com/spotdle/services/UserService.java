package com.spotdle.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotdle.models.UserModel;
import com.spotdle.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserModel findUserById(Long id){
        return userRepository.findById(id).get();
    }
}
