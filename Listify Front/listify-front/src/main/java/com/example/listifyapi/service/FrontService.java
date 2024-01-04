package com.example.listifyapi.service;
import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
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
    public AlbumDto getAlbumById(String id){
        var album = restClient.get().uri(URL+"albums/"+id)
                .retrieve()
                .body(AlbumDto.class);
        return album;
    }
    public List<AlbumDto> getAllAlbums(){
        List<AlbumDto> albums = restClient.get().uri(URL+"albums/get")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        return albums;
    }
    public List<ArtistDto> getAllArtists(){
        List<ArtistDto> artists = restClient.get().uri(URL+"artists/get")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        return artists;
    }
    public void saveArtist(ArtistDto artistDto){
        ResponseEntity<Void> response = restClient.post().uri(URL+"save/artist")
                .contentType(MediaType.APPLICATION_JSON)
                .body(artistDto)
                .retrieve()
                .toBodilessEntity();
    }
    public void saveAlbum(AlbumDto albumDto){
        ResponseEntity<Void> response = restClient.post().uri(URL+"save/album")
                .contentType(MediaType.APPLICATION_JSON)
                .body(albumDto)
                .retrieve()
                .toBodilessEntity();
    }
    public void saveTrack(TrackDto trackDto){
        List<String> idArtist = new ArrayList<>();
                idArtist.add(trackDto.getTempArtist());
        trackDto.setArtists(idArtist);
        ResponseEntity<Void> response = restClient.post().uri(URL+"save/track")
                .contentType(MediaType.APPLICATION_JSON)
                .body(trackDto)
                .retrieve()
                .toBodilessEntity();
    }
    public List<TrackDto> albumsTracks(AlbumDto albumDto){
        return findAllTracks().stream().filter(trackDto -> trackDto.getAlbum().equals(albumDto.getName())).toList();
    }
    public ArtistDto emptyArtist(){
        ArtistDto artist = new ArtistDto();
        String id;
        while (true){
            id = RandomStringUtils.random(15, true, false);
            try{
                artistById(id);
            }
            catch (HttpClientErrorException exception){
                break;
            }
        }
        artist.setSpotifyId(id);
        return artist;
    }
    public AlbumDto emptyAlbum(){
        AlbumDto album = new AlbumDto();
        String id;
        while (true){
            id = RandomStringUtils.random(15, true, false);
            try{
                getAlbumById(id);
            }
            catch (HttpClientErrorException exception){
                break;
            }
        }
        album.setSpotifyId(id);
        return album;
    }
    public TrackDto emptyTrack(){
        TrackDto track = new TrackDto();
        String id;
        while (true){
            id = RandomStringUtils.random(15, true, false);
            try{
                trackById(id);
            }
            catch (HttpClientErrorException exception){
                break;
            }
        }
        track.setSpotifyId(id);
        return track;
    }
}
