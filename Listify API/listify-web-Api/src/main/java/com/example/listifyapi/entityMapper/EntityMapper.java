package com.example.listifyapi.entityMapper;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import com.example.listifyapi.entities.Album;
import com.example.listifyapi.entities.Artist;
import com.example.listifyapi.entities.Track;
import com.example.listifyapi.exceptions.ResourceNotFoundException;
import com.example.listifyapi.repositories.IRepoCatalog;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class EntityMapper implements IEntityMapper{
    private IRepoCatalog repoCatalog;
    @Autowired
    public EntityMapper(IRepoCatalog repoCatalog){
        this.repoCatalog = repoCatalog;
    }

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
        entity.setDurationMs(dto.getDurationMs());
        var album = repoCatalog.getAlbumRepository().findBySpotifyId(dto.getAlbum());
        var artist = repoCatalog.getArtistRepository().getArtistBySpotifyId(dto.getArtists().get(0));
        if(artist.isEmpty() || album.isEmpty()){
            throw new ResourceNotFoundException();
        }
        else{
            entity.setAlbum(album.get());
            album.get().setTotalTracks(album.get().getTotalTracks()+1);
            List<Artist> artists = new ArrayList<>();
            artists.add(artist.get());
            entity.setArtists(artists);
            var artistsTracks = new ArrayList<>(artist.get().getTracks());
            artistsTracks.add(entity);
            artist.get().setTracks(artistsTracks);
        }
        return entity;
    }
}
