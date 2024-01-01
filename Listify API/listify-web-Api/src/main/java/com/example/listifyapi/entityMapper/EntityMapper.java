package com.example.listifyapi.entityMapper;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import com.example.listifyapi.entities.Album;
import com.example.listifyapi.entities.Artist;
import com.example.listifyapi.entities.Track;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EntityMapper implements IEntityMapper{

    @Override
    public Artist mapToArtist(ArtistDto dto) {
        var entity = new Artist();
        entity.setGenres(dto.getGenres());
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setSpotifyId(dto.getSpotifyId());
        entity.setPopularity(dto.getPopularity());
        return entity;
    }
    @Override
    public Album mapToAlbum(AlbumDto dto) {
        var entity = new Album();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAlbumType(dto.getAlbumType());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.setTotalTracks(dto.getTotalTracks());
        entity.setSpotifyId(dto.getSpotifyId());
        return entity;
    }
    @Override
    public Track mapToTrack(TrackDto dto) {
        var entity = new Track();
        entity.setName(dto.getName());
        entity.setSpotifyId(dto.getSpotifyId());
        entity.setExplicit(dto.isExplicit());
        entity.setPopularity(dto.getPopularity());
        entity.setSpotifyUrl(dto.getSpotifyUrl());
        entity.setDurationMs(dto.getDurationMs());
        return entity;
    }
}
