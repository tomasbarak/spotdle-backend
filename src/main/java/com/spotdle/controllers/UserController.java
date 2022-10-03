package com.spotdle.controllers;

import com.spotdle.models.UserModel;

import java.net.URI;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spotdle.services.UserService;

import se.michaelthelin.spotify.*;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<UserModel> obtenerUsuarios(){
        return userService.obtenerUsuarios();
    }

    @PostMapping()
    public UserModel guardarUsuario(UserModel usuario){
        return this.userService.guardarUsuario(usuario);
    }
}
