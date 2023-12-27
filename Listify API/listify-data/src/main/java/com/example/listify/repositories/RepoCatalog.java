package com.example.listify.repositories;

public class RepoCatalog implements IRepoCatalog{
    private ArtistRepository artistRepository;
    private AlbumRepository albumRepository;
    private ImageRepository imageRepository;
    private TrackRepository trackRepository;
    @Override
    public AlbumRepository getAlbumRepository() {
        return albumRepository;
    }
    @Override
    public ArtistRepository getArtistRepository() {
        return artistRepository;
    }
    @Override
    public ImageRepository getImageRepository() {
        return imageRepository;
    }
    @Override
    public TrackRepository getTrackRepository() {
        return trackRepository;
    }
}
