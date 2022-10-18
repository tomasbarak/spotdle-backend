package com.spotdle.controllers;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spotdle.services.UserService;

@RestController
@RequestMapping("/redirect")
public class RedirectController {
    @Autowired
    UserService userService;

    @GetMapping()
    @ResponseBody
    public void redirect(HttpServletResponse response) throws IOException {
        byte[] b = new byte[20];
        new Random().nextBytes(b);
        String state = new String(b, StandardCharsets.UTF_8);
        Cookie spotdle_cookie = new Cookie("state", Hex.encodeHex(b).toString());

        response.addCookie(spotdle_cookie);
        response.sendRedirect("https://accounts.spotify.com/authorize?client_id=17c584ee17464633bd876e27993e4a09&response_type=code&redirect_uri=http://localhost:3000/spotify-redirect&scope=user-read-private%20user-read-email&state=" + state);
    
    }
}
