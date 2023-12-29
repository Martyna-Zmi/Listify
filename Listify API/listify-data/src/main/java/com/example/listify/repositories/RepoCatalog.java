package com.example.listify.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepoCatalog implements IRepoCatalog{
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final TrackRepository trackRepository;
    @Autowired
    public RepoCatalog(AlbumRepository albumRepository, ArtistRepository artistRepository, TrackRepository trackRepository){
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
    }
    @Override
    public AlbumRepository getAlbumRepository() {
        return albumRepository;
    }
    @Override
    public ArtistRepository getArtistRepository() {
        return artistRepository;
    }
    @Override
    public TrackRepository getTrackRepository() {
        return trackRepository;
    }
}
