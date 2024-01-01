package com.example.listifyapi.entityMapper;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import com.example.listifyapi.entities.Album;
import com.example.listifyapi.entities.Artist;
import com.example.listifyapi.entities.Track;

public interface IEntityMapper {
    Artist mapToArtist(ArtistDto dto);
    Album mapToAlbum(AlbumDto dto);
    Track mapToTrack(TrackDto dto);
}
