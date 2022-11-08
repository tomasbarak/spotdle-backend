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

    public UserModel findUserById(String id){
        return userRepository.findById(id).get();
    }

    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }

    public Boolean userExists(String id) {
        return userRepository.existsById(id);
    }
}
