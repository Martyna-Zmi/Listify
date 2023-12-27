package com.example.listify.repositories;

public interface IRepoCatalog {
    AlbumRepository getAlbumRepository();
    ArtistRepository getArtistRepository();
    ImageRepository getImageRepository();
    TrackRepository getTrackRepository();
}
