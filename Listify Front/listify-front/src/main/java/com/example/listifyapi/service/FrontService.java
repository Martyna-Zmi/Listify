package com.example.listifyapi.service;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;
@Service
@Component
public class FrontService {
    private final RestClient restClient = RestClient.create();
    private final String URL = "http://localhost:8081/listify/";
    public List<TrackDto> findAllTracks(){
        List<TrackDto> tracks = restClient.get().uri(URL+"tracks/get")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return tracks;
    }
    public ArtistDto randomArtist(){
        ArtistDto random = restClient.get().uri(URL+"artists/random")
                .retrieve()
                .body(ArtistDto.class);
        return random;
    }
    public TrackDto randomTrack(){
        var random = restClient.get().uri(URL+"tracks/random")
                .retrieve()
                .body(TrackDto.class);
        return random;
    }
    public TrackDto trackById(String id){
        var track = restClient.get().uri(URL+"tracks/"+id)
                .retrieve()
                .body(TrackDto.class);
        return track;
    }
    public ArtistDto artistById(String id){
        var artist = restClient.get().uri(URL+"artists/"+id)
                .retrieve()
                .body(ArtistDto.class);
        return artist;
    }
//    public void saveArtist(ArtistDto artistDto){
//        restClient.post().uri(URL+"artists/new")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(artistDto)
//                .retrieve()
//                .toBodilessEntity();
//    }
}
