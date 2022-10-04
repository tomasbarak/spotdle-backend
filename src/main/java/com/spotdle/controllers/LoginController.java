package com.spotdle.controllers;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;

@RestController
@RequestMapping("/spotify-redirect")
public class LoginController {
    @GetMapping()
    @ResponseBody
    public void returnCredential(@RequestParam String code, HttpServletResponse response, HttpServletRequest request) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId("17c584ee17464633bd876e27993e4a09")
                .setClientSecret("86f590579d084180b23278d10f3b36cd")
                .setRedirectUri(URI.create("http://localhost:3000/spotify-redirect")).build();
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code).build();
        String accessToken = "";
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            accessToken = authorizationCodeCredentials.getAccessToken();

            Cookie spotdle_cookie = new Cookie("spotdle-access", accessToken);

            response.setHeader("Location", "http://localhost:3001");
            response.setStatus(302);

            spotdle_cookie.setSecure(false);
            spotdle_cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(spotdle_cookie);

        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
            response.setStatus(500);
        }

        return;
    }
}
