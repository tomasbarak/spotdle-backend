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

    public UserModel[] getTopUsers(Integer Q) {
        ArrayList<UserModel> users = (ArrayList<UserModel>) userRepository.findAll();
        UserModel[] topUsers = new UserModel[Q];
        for (int i = 0; i < Q; i++) {
            topUsers[i] = users.get(i);
        }
        return topUsers;
    }
}
