package com.example.listifyapi.service;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import com.example.listifyapi.dtoMapper.IDtoMapper;
import com.example.listifyapi.entities.Album;
import com.example.listifyapi.exceptions.ResourceNotFoundException;
import com.example.listifyapi.repositories.RepoCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            throw new ResourceNotFoundException();
        }
        return dtoMapper.mapTrack(entity.get());
    }
    public List<TrackDto> getPopularSongs(){
        var entities = repoCatalog.getTrackRepository().searchAllByPopularityGreaterThanEqual(80);
        return entities.stream().map(dtoMapper::mapTrack).toList();
    }
    public List<ArtistDto> getAllArtists(){
        var entities = repoCatalog.getArtistRepository().findAll();
        return entities.stream().map(dtoMapper::mapArtist).toList();
    }
    public void deleteTrack(String id){
        var entity = repoCatalog.getTrackRepository().getTrackBySpotifyId(id);
        if(entity.isEmpty()){
            throw new ResourceNotFoundException();
        }
        repoCatalog.getTrackRepository().delete(entity.get());
    }
    public ArtistDto getArtistById(String id){
        var entity = repoCatalog.getArtistRepository().getArtistBySpotifyId(id);
        if(entity.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return dtoMapper.mapArtist(entity.get());
    }
    public List<ArtistDto> getArtistsByGenre(String genre){
        var artists = repoCatalog.getArtistRepository().searchAllByGenresContaining(genre);
        var results = artists.stream().map(dtoMapper::mapArtist).toList();
        return results;
    }
    public List<AlbumDto> getArtistsAlbums(String id){
        var artist = repoCatalog.getArtistRepository().getArtistBySpotifyId(id);
        List<Album> albums = new ArrayList<>();
        if(artist.isPresent()){
            artist.get().getTracks().forEach(track -> {
                if(!albums.contains(track.getAlbum())){
                    albums.add(track.getAlbum());
                }
            });
        }
        else{
            throw new ResourceNotFoundException();
        }
        return albums.stream().map(dtoMapper::mapAlbum).toList();
    }
    public List<ArtistDto> getPopularArtists(){
        var entities = repoCatalog.getArtistRepository().searchAllByPopularityGreaterThanEqual(70);
        return entities.stream().map(dtoMapper::mapArtist).toList();
    }
    public void deleteArtist(String id){
        var entity = repoCatalog.getArtistRepository().getArtistBySpotifyId(id);
        if(entity.isEmpty()){
            throw new ResourceNotFoundException();
        }
        repoCatalog.getArtistRepository().delete(entity.get());
    }
    public AlbumDto getAlbumById(String id){
        var entity = repoCatalog.getAlbumRepository().findBySpotifyId(id);
        if(entity.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return dtoMapper.mapAlbum(entity.get());
    }
    public void deleteAlbum(String id){
        var entity = repoCatalog.getAlbumRepository().findBySpotifyId(id);
        if(entity.isEmpty()){
            throw new ResourceNotFoundException();
        }
        repoCatalog.getAlbumRepository().delete(entity.get());
    }
}
