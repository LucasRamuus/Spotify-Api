package Nedless.spotify.controller;

import Nedless.spotify.client.Album;
import Nedless.spotify.client.AlbumSpotifyClient;
import Nedless.spotify.client.AuthSpotifyClient;
import Nedless.spotify.client.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")

public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;

    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWorld(){
        var request = new LoginRequest(
                "client_credentials",
                "4d436ba7d82946c59fac4cb56674e117",
                "a56fc6ff8e774c6b92c888c105123964"

        );
        var token = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getReleases("Bearer " + token);


        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
