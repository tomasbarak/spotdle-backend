package com.spotdle.controllers;

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
    
    @GetMapping()
    public Album getAlbum(@RequestParam String id, @CookieValue("accessToken") String accessToken){
        SpotifyService spotifyService = new SpotifyService(accessToken);
        Album album = spotifyService.getAlbum(id);
        return album;
    }

    @GetMapping("/images")
    public Image[] getAlbumImages(@RequestParam String id, @CookieValue(name = "spotdle-access") String accessToken){
        SpotifyService spotifyService = new SpotifyService(accessToken);
        return spotifyService.getAlbum(id).getImages();
    }
}
