package com.example.listify.mappers;

import com.example.listify.entities.Album;
import com.example.listify.entities.Artist;
import com.example.listify.entities.Track;
import com.example.listify.spotifyDto.AlbumDtoSp;
import com.example.listify.spotifyDto.ArtistDtoSp;
import com.example.listify.spotifyDto.TrackDtoSp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class Mapper implements IMapper {
    private final IConvertList listConverter;

    @Autowired
    public Mapper(IConvertList listConverter){
        this.listConverter = listConverter;
    }
    @Override
    public Artist mapArtist(ArtistDtoSp dto) {
        var artist = new Artist();
        artist.setName(dto.getName());
        if(dto.getImage()!=null){
            artist.setImage(dto.getImage().get(0).getUrl());
        }
        artist.setGenres(Arrays.toString(dto.getGenres()));
        artist.setSpotifyId(dto.getSpotifyId());
        return artist;
    }
    @Override
    public Album mapAlbum(AlbumDtoSp dto) {
        var album = new Album();
        album.setSpotifyId(dto.getSpotifyId());
        album.setName(dto.getName());
        album.setImage(dto.getImage().get(0).getUrl());
        album.setAlbumType(dto.getAlbumType());
        album.setTotalTracks(dto.getTotalTracks());
        album.setReleaseDate(dto.getReleaseDate());
        return album;
    }
    @Override
    public Track mapTrack(TrackDtoSp dto) {
        var track = new Track();
        track.setSpotifyId(dto.getSpotifyId());
        track.setDurationMs(dto.getDurationMs());
        track.setIsExplicit(dto.isExplicit());
        track.setSpotifyUrl(dto.getSpotifyUrl());
        track.setName(dto.getName());
        track.setAlbum(mapAlbum(dto.getAlbum()));
        return track;
    }
}
