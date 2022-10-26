package com.spotdle.controllers;

import com.spotdle.models.UserModel;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spotdle.services.SpotifyService;
import com.spotdle.services.UserService;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.specification.Paging;

@RestController
@RequestMapping("/me/user")
public class UserController {
    @Autowired
    UserService userService;
    
    @Value("${url.auth.redirect}")
    private String redirectUrl;
    
    @GetMapping()
    public UserModel getUser(@CookieValue("spotdle-access") String accessToken, HttpServletResponse response, HttpServletRequest request) throws ParseException, SpotifyWebApiException, IOException {
        SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
        String id = spotifyService.getUser().getId();
        return userService.findUserById(id);
    }

    @GetMapping("/name")
    public String getMyUserName(@CookieValue("spotdle-access") String accessToken, HttpServletResponse response, HttpServletRequest request) throws ParseException, SpotifyWebApiException, IOException{
        SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
        String id = spotifyService.getUser().getId();
        return userService.findUserById(id).getName();
    }

    @GetMapping("/email")
    public String getMyUserEmail(@CookieValue("spotdle-access") String accessToken, HttpServletResponse response, HttpServletRequest request) throws ParseException, SpotifyWebApiException, IOException{
        SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
        String id = spotifyService.getUser().getId();
        return userService.findUserById(id).getEmail();
    }

    @GetMapping("/image")
    public Image[] getMyUserImage(@CookieValue("spotdle-access") String accessToken, HttpServletResponse response, HttpServletRequest request) throws ParseException, SpotifyWebApiException, IOException{
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET");
        SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
        return spotifyService.getCurrentUser().getImages();
    }
    @GetMapping("/topartists")
    public Paging<Artist> getMyTopArtists(@CookieValue("spotdle-access") String accessToken, HttpServletResponse response, HttpServletRequest request) throws ParseException, SpotifyWebApiException, IOException{
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET");
        SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
        return spotifyService.getUserTopArtists(15);
    }
    
    @GetMapping("/artist")
    public Artist getArtist(@RequestParam String id, @CookieValue("spotdle-access") String accessToken, HttpServletResponse response, HttpServletRequest request) throws ParseException, SpotifyWebApiException, IOException {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET");
        SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
        return spotifyService.getArtist(id);
    }

    @GetMapping("/artist/albums")
    public AlbumSimplified[] getArtistAlbums(@RequestParam String id, @CookieValue("spotdle-access") String accessToken, HttpServletResponse response, HttpServletRequest request) throws ParseException, SpotifyWebApiException, IOException {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET");
        SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
        return spotifyService.getArtistAlbums(id);
    }
}

