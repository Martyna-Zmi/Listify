package com.example.listify.mappers;

import com.example.listify.entities.Album;
import com.example.listify.entities.Artist;
import com.example.listify.entities.Track;
import com.example.listify.spotifyDto.AlbumDtoSp;
import com.example.listify.spotifyDto.ArtistDtoSp;
import com.example.listify.spotifyDto.TrackDtoSp;

public interface IMapper{
    Artist mapArtist(ArtistDtoSp dto);
    Album mapAlbum(AlbumDtoSp dto);
    Track mapTrack(TrackDtoSp dto);
}
