package com.example.listify.service;

import com.example.listify.dto.AlbumDto;
import com.example.listify.dto.ArtistDto;
import com.example.listify.dto.TrackDto;
import com.example.listify.dtoMapper.IDtoMapper;
import com.example.listify.entities.Album;
import com.example.listify.repositories.RepoCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class ListifyService {
    private final RepoCatalog repoCatalog;
    private final IDtoMapper dtoMapper;
    @Autowired
    public ListifyService(RepoCatalog repoCatalog, IDtoMapper dtoMapper){
        this.repoCatalog = repoCatalog;
        this.dtoMapper = dtoMapper;
    }
    public List<TrackDto> getAllTracks(){
        var entities = repoCatalog.getTrackRepository().findAll();
        return entities.stream().map(dtoMapper::mapTrack).toList();
    }
    public TrackDto getTrackById(String id){
        var entity = repoCatalog.getTrackRepository().getTrackBySpotifyId(id);
        if(entity.isEmpty()){
            return null;
            //TODO throw custom exception
        }
        return dtoMapper.mapTrack(entity.get());
    }
    public List<ArtistDto> getAllArtists(){
        var entities = repoCatalog.getArtistRepository().findAll();
        return entities.stream().map(dtoMapper::mapArtist).toList();
    }
    public ArtistDto getArtistById(String id){
        return dtoMapper.mapArtist(repoCatalog.getArtistRepository().getArtistBySpotifyId(id));
    }
    public List<ArtistDto> getArtistsByGenre(String genre){
        var artists = getAllArtists();
        List<ArtistDto> entities = artists.stream()
                .filter(artist->artist.getGenres()
                .contains(genre))
                .toList();
        return entities;
    }
    public List<AlbumDto> getArtistsAlbums(String id){
        var artist = repoCatalog.getArtistRepository().getArtistBySpotifyId(id);
        List<Album> albums = new ArrayList<>();
        artist.getTracks().forEach(track -> {
            if(!albums.contains(track)){
                albums.add(track.getAlbum());
            }
        });
        return albums.stream().map(dtoMapper::mapAlbum).toList();
    }
}
