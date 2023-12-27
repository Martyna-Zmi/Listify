package com.example.listify;

import com.example.listify.spotifyDto.TopTracksDtoSp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class UpdaterController {
    @Value("${spotify.clientId}")
    private String spotifyClientId;
    @Value("${spotify.clientSecret}")
    private String spotifyClientSecret;
    @Value("${spotify.hourlyKey}")
    private String hourlyKey;
    private TopTracksDtoSp searchPage;
    private final String URL = "https://api.spotify.com/v1/";
    private RestClient restClient = RestClient.create();
    @GetMapping("listify/update-data/{artistId}")
    public void updateWithTop(@PathVariable("artistId") String artistId){
        //TODO log about update
        var resultPage = restClient.get()
                .uri(URL+"artists/"+artistId+"/top-tracks?market=PL")
                .header("Authorization", "Bearer " + hourlyKey)
                .retrieve()
                .body(TopTracksDtoSp.class);
        this.searchPage = resultPage;
        System.out.println(resultPage.getTracks().get(0).getName());
        //TODO map to entity and save to database :)
    }

}
