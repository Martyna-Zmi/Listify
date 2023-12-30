package com.example.listifyapi.repositories;

public interface IRepoCatalog {
    AlbumRepository getAlbumRepository();
    ArtistRepository getArtistRepository();
    TrackRepository getTrackRepository();
}
