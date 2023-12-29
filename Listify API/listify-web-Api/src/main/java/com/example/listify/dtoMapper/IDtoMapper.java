package com.example.listify.dtoMapper;

import com.example.listify.dto.AlbumDto;
import com.example.listify.dto.ArtistDto;
import com.example.listify.dto.TrackDto;
import com.example.listify.entities.Album;
import com.example.listify.entities.Artist;
import com.example.listify.entities.Track;

public interface IDtoMapper {
    AlbumDto mapAlbum(Album album);
    ArtistDto mapArtist(Artist artist);
    TrackDto mapTrack(Track track);
}
