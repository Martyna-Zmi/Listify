package com.example.listify.repositories;

import org.springframework.stereotype.Component;

public interface IRepoCatalog {
    AlbumRepository getAlbumRepository();
    ArtistRepository getArtistRepository();
    TrackRepository getTrackRepository();
}
