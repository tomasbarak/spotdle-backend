package com.spotdle.controllers;

import com.spotdle.models.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spotdle.services.UserService;

@RestController
@RequestMapping("/me/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public UserModel getUser(@RequestParam String id){
        return userService.findUserById(id);
    }

    @GetMapping("/name")
    public String getMyUserName(@RequestParam String id){
        return userService.findUserById(id).getName();
    }

    @GetMapping("/email")
    public String getMyUserEmail(@RequestParam String id){
        return userService.findUserById(id).getEmail();
    }
    
}

