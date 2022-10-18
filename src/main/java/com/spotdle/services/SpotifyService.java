package com.spotdle.services;

import java.io.IOException;
import java.net.URI;

import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import com.spotdle.models.UserModel;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;

public class SpotifyService {
    private String accessToken;
    private SpotifyApi spotifyApi;

    public SpotifyService(String accessToken) {
        this.accessToken = accessToken;
        this.spotifyApi = new SpotifyApi.Builder()
        .setClientId("17c584ee17464633bd876e27993e4a09")
        .setClientSecret("86f590579d084180b23278d10f3b36cd")
        .setRedirectUri(URI.create("http://localhost:3000/spotify-redirect")).build();
        this.spotifyApi.setAccessToken(accessToken);
    }

    public UserModel getUser() throws ParseException, SpotifyWebApiException, IOException {
        UserModel user = new UserModel();
        user.setName(this.spotifyApi.getCurrentUsersProfile().build().execute().getDisplayName());
        user.setEmail(this.spotifyApi.getCurrentUsersProfile().build().execute().getEmail());
        user.setId(this.spotifyApi.getCurrentUsersProfile().build().execute().getId());
        return user;
    }

    public Album getAlbum(String albumId) {
        GetAlbumRequest getAlbumRequest = this.spotifyApi.getAlbum(albumId).build();

        try {
            Album album = getAlbumRequest.execute();
            return album;
          } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
          }
    }
}
