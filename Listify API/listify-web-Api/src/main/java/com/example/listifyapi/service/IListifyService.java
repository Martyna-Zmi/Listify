package com.example.listifyapi.service;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;

import java.util.List;

public interface IListifyService {
    List<TrackDto> getAllTracks();
    TrackDto getTrackById(String id);
    List<TrackDto> getPopularSongs();
    List<ArtistDto> getAllArtists();
    TrackDto getRandomTrack();
    ArtistDto getArtistById(String id);
    List<ArtistDto> getArtistsByGenre(String genre);
    ArtistDto getRandomArtist();
    AlbumDto getAlbumById(String id);
    List<AlbumDto> getAlbums();
    List<AlbumDto> getArtistsAlbums(String id);
    List<ArtistDto> getPopularArtists();
    void saveArtist(ArtistDto artistDto);
    void saveAlbum(AlbumDto albumDto);
    void saveTrack(TrackDto trackDto);
    void updateTrack(TrackDto trackDto);
    void updateArtist(ArtistDto artistDto);
    void updateAlbum(AlbumDto albumDto);
    void deleteArtist(String id);
    void deleteAlbum(String id);
    void deleteTrack(String id);
}
