package com.spotdle.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spotdle.services.SpotifyService;

import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.Image;

@RestController
@RequestMapping("/album")
public class AlbumController {
    
    @Value("${url.auth.redirect}")
    private String redirectUrl;

    @GetMapping()
    public Album getAlbum(@RequestParam String id, @CookieValue("accessToken") String accessToken, HttpServletResponse response, HttpServletRequest request) {
        SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
        Album album = spotifyService.getAlbum(id);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET");
        return album;
    }

    @GetMapping("/images")
    public Image[] getAlbumImages(@RequestParam String id, @CookieValue(name = "spotdle-access") String accessToken, HttpServletResponse response, HttpServletRequest request){
        SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET");
        return spotifyService.getAlbum(id).getImages();
    }
}
