package com.example.listifyapi.mappers;

import com.example.listifyapi.entities.Album;
import com.example.listifyapi.entities.Artist;
import com.example.listifyapi.entities.Track;
import com.example.listifyapi.spotifyDto.AlbumDtoSp;
import com.example.listifyapi.spotifyDto.ArtistDtoSp;
import com.example.listifyapi.spotifyDto.TrackDtoSp;

public interface IMapper{
    Artist mapArtist(ArtistDtoSp dto);
    Album mapAlbum(AlbumDtoSp dto);
    Track mapTrack(TrackDtoSp dto);
}
