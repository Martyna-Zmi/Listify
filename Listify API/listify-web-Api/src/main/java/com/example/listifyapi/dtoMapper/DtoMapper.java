package com.example.listifyapi.dtoMapper;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import com.example.listifyapi.entities.Album;
import com.example.listifyapi.entities.Artist;
import com.example.listifyapi.entities.Track;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Data
@NoArgsConstructor
@Component
public class DtoMapper implements IDtoMapper{
    @Override
    public AlbumDto mapAlbum(Album album) {
        var dto = new AlbumDto();
        dto.setName(album.getName());
        dto.setAlbumType(album.getAlbumType());
        dto.setImage(album.getImage());
        dto.setReleaseDate(album.getReleaseDate());
        dto.setSpotifyId(album.getSpotifyId());
        dto.setTotalTracks(album.getTotalTracks());
        return dto;
    }
    @Override
    public ArtistDto mapArtist(Artist artist) {
        var dto = new ArtistDto();
        dto.setSpotifyId(artist.getSpotifyId());
        dto.setGenres(artist.getGenres());
        dto.setImage(artist.getImage());
        dto.setName(artist.getName());
        dto.setPopularity(artist.getPopularity());
        return dto;
    }
    @Override
    public TrackDto mapTrack(Track track) {
        var dto = new TrackDto();
        dto.setSpotifyId(track.getSpotifyId());
        dto.setExplicit(track.isExplicit());
        dto.setDurationMs(track.getDurationMs());
        dto.setName(track.getName());
        dto.setImage(track.getAlbum().getImage());
        dto.setPopularity(track.getPopularity());
        dto.setArtists(track.getArtists().stream().map(Artist::getName).toList());
        dto.setAlbum(track.getAlbum().getName());
        return dto;
    }
}
