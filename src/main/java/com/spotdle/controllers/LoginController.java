package com.spotdle.controllers;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spotdle.models.UserModel;
import com.spotdle.services.SpotifyService;
import com.spotdle.services.UserService;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;

@RestController
@RequestMapping("/spotify-redirect")
public class LoginController {
    @Autowired
    UserService userService;

    @Value("${url.frontendDomain}")
    private String frontendDomain;

    @Value("${url.frontendDomainName}")
    private String frontendDomainName;

    @Value("${url.backendDomain}")
    private String backendDomain;
    
    @Value("${url.backendDomainName}")
    private String backendDomainName;

    @Value("${url.auth.redirect}")
    private String redirectUrl;


    @GetMapping()
    @ResponseBody
    public void returnCredential(@RequestParam String code, HttpServletResponse response, HttpServletRequest request) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId("17c584ee17464633bd876e27993e4a09")
        .setClientSecret("86f590579d084180b23278d10f3b36cd")
        .setRedirectUri(URI.create(this.redirectUrl.toString()))
        .build();

        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code).build();
        String accessToken = "";
        try {
            //System.out.println(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            accessToken = authorizationCodeCredentials.getAccessToken();
            SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
            UserModel user = spotifyService.getUser();
            userService.saveUser(user);
            Cookie spotdle_cookie = new Cookie("spotdle-access", accessToken);


            spotdle_cookie.setSecure(false);
            spotdle_cookie.setDomain(this.frontendDomainName);
            spotdle_cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(spotdle_cookie);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", this.backendDomain);
            response.setHeader("Access-Control-Allow-Methods", "GET");
            response.setHeader("Location", this.frontendDomain);
            response.setStatus(302);
        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
            response.setStatus(500);
        }

        return;
    }
}
