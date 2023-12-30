package com.example.listifyapi.updater;

import com.example.listifyapi.spotifyDto.ArtistDtoSp;
import com.example.listifyapi.spotifyDto.TopTracksDtoSp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;


@RestController
public class UpdaterController {
    private Updater updaterService;
    @Value("${spotify.clientId}")
    private String spotifyClientId;
    @Value("${spotify.clientSecret}")
    private String spotifyClientSecret;
    @Value("${spotify.hourlyKey}")
    private String hourlyKey;
    private TopTracksDtoSp searchPage;
    private final String URL = "https://api.spotify.com/v1/";
    private RestClient restClient = RestClient.create();
    @Autowired
    public UpdaterController(Updater updater){
        this.updaterService = updater;
    }
    @GetMapping("listify/update-data/{artistId}")
    public void updateWithTop(@PathVariable("artistId") String artistId){
        //TODO log about update
        var resultPage = restClient.get()
                .uri(URL+"artists/"+artistId+"/top-tracks?market=PL")
                .header("Authorization", "Bearer " + hourlyKey)
                .retrieve()
                .body(TopTracksDtoSp.class);

            var artist = restClient.get()
                .uri(URL+"artists/"+artistId)
                .header("Authorization", "Bearer " + hourlyKey)
                .retrieve()
                .body(ArtistDtoSp.class);
            ArrayList<ArtistDtoSp> foundArtists = new ArrayList<>();
        updaterService.mapFound(resultPage, artist);
    }


}
