package com.example.listifyapi.mappers;

import com.example.listifyapi.entities.Album;
import com.example.listifyapi.entities.Artist;
import com.example.listifyapi.entities.Track;
import com.example.listifyapi.spotifyDto.AlbumDtoSp;
import com.example.listifyapi.spotifyDto.ArtistDtoSp;
import com.example.listifyapi.spotifyDto.TrackDtoSp;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Arrays;
@NoArgsConstructor
@Component
public class Mapper implements IMapper {

    @Override
    public Artist mapArtist(ArtistDtoSp dto) {
        var artist = new Artist();
        artist.setName(dto.getName());
        if(dto.getImage()!=null){
            artist.setImage(dto.getImage().get(0).getUrl());
        }
        String genres = Arrays.toString(dto.getGenres())
                       .replace("[","")
                       .replace("]","");
        artist.setGenres(genres);
        artist.setSpotifyId(dto.getSpotifyId());
        artist.setImage(dto.getImage().get(0).getUrl());
        artist.setPopularity(dto.getPopularity());
        return artist;
    }
    @Override
    public Album mapAlbum(AlbumDtoSp dto) {
        var album = new Album();
        album.setSpotifyId(dto.getSpotifyId());
        album.setName(dto.getName());
        album.setImage(dto.getImage().get(0).getUrl());
        album.setAlbumType(dto.getAlbumType());
        album.setTotalTracks(0);
        album.setReleaseDate(dto.getReleaseDate());
        return album;
    }
    @Override
    public Track mapTrack(TrackDtoSp dto) {
        var track = new Track();
        track.setSpotifyId(dto.getSpotifyId());
        track.setDurationMs(dto.getDurationMs());
        track.setIsExplicit(dto.isExplicit());
        track.setName(dto.getName());
        track.setAlbum(mapAlbum(dto.getAlbum()));
        track.setPopularity(dto.getPopularity());
        return track;
    }
}
