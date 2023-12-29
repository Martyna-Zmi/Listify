package com.example.listify.updater;

import com.example.listify.entities.Album;
import com.example.listify.entities.Artist;
import com.example.listify.entities.Track;
import com.example.listify.mappers.IMapper;
import com.example.listify.repositories.IRepoCatalog;
import com.example.listify.spotifyDto.*;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.utility.ListIterate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class Updater {
    private IRepoCatalog repoCatalog;
    private IMapper mapper;
    private RestClient restClient = RestClient.create();
    @Value("${spotify.hourlyKey}")
    private String hourlyKey;
    @Autowired
    public Updater(IRepoCatalog repoCatalog, IMapper mapper){
        this.repoCatalog = repoCatalog;
        this.mapper = mapper;
    }
    public void mapFound(TopTracksDtoSp searchPage, ArtistDtoSp mainArtist){
        List<TrackDtoSp> trackDtos = searchPage.getTracks();
        List<ArtistDtoSp> artistDtos = new ArrayList<>();
        searchPage.getTracks().forEach(track->artistDtos.addAll(track.getArtists()));
        List<ArtistDtoSp> uniqueArtists = ListIterate.distinct(artistDtos, HashingStrategies.fromFunction(ArtistDtoSp::getSpotifyId));
        List<Artist> artists = new ArrayList<>();
        for (ArtistDtoSp dto:
             uniqueArtists) {
            var fullArtistDto = restClient.get()
                    .uri("https://api.spotify.com/v1/artists/"+dto.getSpotifyId())
                    .header("Authorization", "Bearer " + hourlyKey)
                    .retrieve()
                    .body(ArtistDtoSp.class);
            artists.add(mapper.mapArtist(fullArtistDto));
        }
        //List<Artist> artistsDuplicates = artistDtos.stream().map(dto->mapper.mapArtist(dto)).toList();
        //List<Artist> artists = ListIterate.distinct(artistsDuplicates, HashingStrategies.fromFunction(Artist::getSpotifyId));


        //artists.get(0).setGenres(Arrays.toString(mainArtist.getGenres()));
        //artists.get(0).setImage(mainArtist.getImage().get(0).getUrl());

        List<Album> albumsDuplicates = trackDtos.stream().map(dto->mapper.mapAlbum(dto.getAlbum())).toList();
        List<Album> albums = ListIterate.distinct(albumsDuplicates, HashingStrategies.fromFunction(Album::getSpotifyId));
        List<Track> tracks = trackDtos.stream().map(dto->mapper.mapTrack(dto)).toList();

        albums.forEach(album -> repoCatalog.getAlbumRepository().save(album));
        tracks.forEach(track-> repoCatalog.getTrackRepository().save(track));
        artists.forEach(artist-> repoCatalog.getArtistRepository().save(artist));

        for(int i=0; i<trackDtos.size(); i++){ // album -> tracks & track -> album
            String id = trackDtos.get(i).getAlbum().getSpotifyId();
            Album tempAlbum = albums.stream().filter(album -> id.equals(album.getSpotifyId())).findFirst().orElse(null);
            tracks.get(i).setAlbum(tempAlbum);
            if(tempAlbum.getTracks()==null){
                var placeholderTracks = new ArrayList<Track>();
                tempAlbum.setTracks(placeholderTracks);
            }
            var tempTracks = new ArrayList<>(tempAlbum.getTracks());
            tempTracks.add(tracks.get(i));
            tempAlbum.setTracks(tempTracks);
        }
        for(int i=0; i<trackDtos.size(); i++){ //track -> artists, artist-> tracks
            final Track track = tracks.get(i);
            List<String> artistIdsDuplicates = trackDtos.get(i).getArtists().stream().map(ArtistDtoSp::getSpotifyId).toList();
            final List<String> artistIds = artistIdsDuplicates.stream().distinct().toList();
            if(tracks.get(i).getArtists()==null){
                var placeholderArtists = new ArrayList<Artist>();
                track.setArtists(placeholderArtists);
            }
            artists.forEach(artist -> {
              if(artist.getTracks()==null){
                  var placeholderTracks = new ArrayList<Track>();
                  artist.setTracks(placeholderTracks);
              }
              if(artistIds.contains(artist.getSpotifyId())){
                  var tempTrack = new ArrayList<>(artist.getTracks());
                  tempTrack.add(track);
                  artist.setTracks(tempTrack);
                  var tempArtists = new ArrayList<Artist>(track.getArtists());
                  tempArtists.add(artist);
                  track.setArtists(tempArtists);
              }
            });
        }
        tracks.forEach(track -> repoCatalog.getTrackRepository().save(track));
    }
}
