package com.example.listify.updater;

import com.example.listify.entities.Album;
import com.example.listify.entities.Artist;
import com.example.listify.entities.Track;
import com.example.listify.mappers.IMapper;
import com.example.listify.repositories.IRepoCatalog;
import com.example.listify.spotifyDto.AlbumDtoSp;
import com.example.listify.spotifyDto.ArtistDtoSp;
import com.example.listify.spotifyDto.TopTracksDtoSp;
import com.example.listify.spotifyDto.TrackDtoSp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class Updater {
    private IRepoCatalog repoCatalog;
    private IMapper mapper;
    @Autowired
    public Updater(IRepoCatalog repoCatalog, IMapper mapper){
        this.repoCatalog = repoCatalog;
        this.mapper = mapper;
    }
    public void mapFound(TopTracksDtoSp searchPage, List<ArtistDtoSp> foundArtists){
        List<TrackDtoSp> trackDtos = searchPage.getTracks();
        List<AlbumDtoSp> albumDtos = new ArrayList<>();
        List<ArtistDtoSp> artistDtos = foundArtists;
        for (TrackDtoSp trackdto:
                trackDtos) {
            albumDtos.add(trackdto.getAlbum());
        }
        List<Artist> artists = new ArrayList<>(artistDtos.stream().map(dto->mapper.mapArtist(dto)).toList());
        List<Album> albums = new ArrayList<>(albumDtos.stream().map(dto->mapper.mapAlbum(dto)).toList());
        List<Track> tracks = new ArrayList<>(trackDtos.stream().map(dto->mapper.mapTrack(dto)).toList());
        //relations done: artists' tracks, artists' albums, tracks' artist, tracks' album, albums' tracks, albums' artist
        artists.forEach(artist->{
            if(artist.getTracks()==null){
                ArrayList<Track> placeholder = new ArrayList<>();
                artist.setTracks(placeholder);
            }
            List<Track> list = new ArrayList<>(artist.getTracks());
            list.addAll(tracks);
            artist.setTracks(list);
            tracks.forEach(track ->{
                if(track.getArtists()==null){
                    List<Artist> placeholder = new ArrayList<>();
                    track.setArtists(placeholder);
                }
                if(artist.getAlbums()==null){
                    List<Album> placeholderAlbum = new ArrayList<>();
                    artist.setAlbums(placeholderAlbum);
                }
                List<Artist> artistsTemp = new ArrayList<>(track.getArtists());
                artistsTemp.add(artist);
                track.setArtists(artistsTemp);
                artist.setAlbums(albums);
            });
        });
        for (Artist artist:
             artists) {
            for (Album album:
                    artist.getAlbums()) {
                for (Track track:
                        artist.getTracks()) {
                    if(track.getAlbum().getSpotifyId().equals(album.getSpotifyId())){
                        if(album.getTracks()==null){
                            List<Track> placeholder = new ArrayList<>();
                            album.setTracks(placeholder);
                        }
                        if(album.getArtists()==null){
                            List<Artist> artistPlaceholder = new ArrayList<>();
                            album.setArtists(artistPlaceholder);
                        }
                        var list = new ArrayList<>(album.getTracks());
                        list.add(track);
                        album.setTracks(list);
                        var artistList = new ArrayList<>(album.getArtists());
                        artistList.add(artists.get(0));
                        album.setArtists(artistList);
                    }
                }
            }
        }

//        for (Album album:
//             albums) {
//            for (Track track:
//                 tracks) {
//                if(track.getAlbum().getSpotifyId().equals(album.getSpotifyId())){
//                    if(album.getTracks()==null){
//                        List<Track> placeholder = new ArrayList<>();
//                        album.setTracks(placeholder);
//                    }
//                    if(album.getArtists()==null){
//                        List<Artist> artistPlaceholder = new ArrayList<>();
//                        album.setArtists(artistPlaceholder);
//                    }
//                    var list = new ArrayList<>(album.getTracks());
//                    list.add(track);
//                    album.setTracks(list);
//                    var artistList = new ArrayList<>(album.getArtists());
//                    artistList.add(artists.get(0));
//                    album.setArtists(artistList);
//                }
//            }
//        }
        //saver(artists);
        artists.forEach(artist -> repoCatalog.getArtistRepository().save(artist));
    }
    public void saver(List<Artist> artists){

    }
}
