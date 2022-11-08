package com.spotdle.controllers;

import com.spotdle.models.UserModel;

import java.io.IOException;
import java.util.Enumeration;

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
@RequestMapping("/me/user/ranking")
public class RankingController {
    @Autowired
    UserService userService;
    
    @Value("${url.auth.redirect}")
    private String redirectUrl;

    @GetMapping()
    public Integer getMaxScore(@CookieValue("spotdle-access") String accessToken, HttpServletResponse response, HttpServletRequest request) throws ParseException, SpotifyWebApiException, IOException {
        SpotifyService spotifyService = new SpotifyService(accessToken, this.redirectUrl);
        String userId = spotifyService.getCurrentUser().getId();
        UserModel currentUser = this.userService.findUserById(userId);
        return currentUser.getMaxScore();
    }
    
    @GetMapping("/save")
    public void saveRanking(@CookieValue("spotdle-access") String accessToken, HttpServletResponse response, HttpServletRequest request) throws ParseException, SpotifyWebApiException, IOException {
    }
}

