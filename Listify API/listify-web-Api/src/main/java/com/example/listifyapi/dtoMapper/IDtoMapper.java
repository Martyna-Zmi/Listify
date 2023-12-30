package com.example.listifyapi.dtoMapper;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import com.example.listifyapi.entities.Album;
import com.example.listifyapi.entities.Artist;
import com.example.listifyapi.entities.Track;

public interface IDtoMapper {
    AlbumDto mapAlbum(Album album);
    ArtistDto mapArtist(Artist artist);
    TrackDto mapTrack(Track track);
}
