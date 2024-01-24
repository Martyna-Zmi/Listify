package com.example.listifyapi.service;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;

import java.util.List;

public interface IFrontService {
    List<TrackDto> findAllTracks();
    ArtistDto randomArtist();
    TrackDto randomTrack();
    TrackDto trackById(String id);
    ArtistDto artistById(String id);
    AlbumDto getAlbumById(String id);
    List<AlbumDto> getAllAlbums();
    List<ArtistDto> getAllArtists();
    List<TrackDto> albumsTracks(AlbumDto albumDto);
    void saveArtist(ArtistDto artistDto);
    void saveTrack(TrackDto trackDto);
    void saveAlbum(AlbumDto albumDto);
    void updateAlbum(AlbumDto albumDto);
    void updateTrack(TrackDto trackDto);
    void updateArtist(ArtistDto artistDto);
    void deleteTrack(String id);
    void deleteArtist(String id);
    void deleteAlbum(String id);
    ArtistDto emptyArtist();
    AlbumDto emptyAlbum();
    TrackDto emptyTrack();
}
