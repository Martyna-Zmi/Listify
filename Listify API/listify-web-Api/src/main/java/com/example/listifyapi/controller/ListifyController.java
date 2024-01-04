package com.example.listifyapi.controller;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import com.example.listifyapi.service.ListifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("listify/")
@RestController
public class ListifyController {
    private final ListifyService listifyService;
    @Autowired
    public ListifyController(ListifyService listifyService){
        this.listifyService = listifyService;
    }
   //Tracks
    //TODO update track
   @PostMapping("save/track")
   public ResponseEntity saveTrack(@RequestBody TrackDto trackDto){
       listifyService.saveTrack(trackDto);
       return ResponseEntity.ok(HttpStatus.ACCEPTED);
   }
    @GetMapping("tracks/get")
    public ResponseEntity getAllTracks(){
        var results = listifyService.getAllTracks();
        return ResponseEntity.ok(results);
    }
    @GetMapping("tracks/{id}")
    public ResponseEntity getTrackById(@PathVariable("id") String id){
        var result = listifyService.getTrackById(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("tracks/trending")
    public ResponseEntity getPopularTracks(){
        var results = listifyService.getPopularSongs();
        return ResponseEntity.ok(results);
        }
     @DeleteMapping("tracks/{id}/delete")
     public ResponseEntity deleteTrack(@PathVariable("id")String id){
        listifyService.deleteTrack(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
     }
     @GetMapping("tracks/random")
     public ResponseEntity getRandomTrack(){
        return ResponseEntity.ok(listifyService.getRandomTrack());
     }
    //Artists
    //TODO update artist
    @GetMapping("artists/get")
    public ResponseEntity getAllArtists(){
        var results = listifyService.getAllArtists();
        return ResponseEntity.ok(results);
    }
    @GetMapping("artists/{id}")
    public ResponseEntity artistById(@PathVariable("id")String id){
        var result = listifyService.getArtistById(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("genres/{genre}")
    public ResponseEntity getArtistsByGenre(@PathVariable("genre")String genre){
        var results = listifyService.getArtistsByGenre(genre);
        return ResponseEntity.ok(results);
    }
    @GetMapping("artists/{id}/albums")
    public ResponseEntity getArtistsAlbums(@PathVariable("id")String id){
        var results = listifyService.getArtistsAlbums(id);
        return ResponseEntity.ok(results);
    }
    @GetMapping("artists/trending")
    public ResponseEntity getPopularArtists(){
        var results = listifyService.getPopularArtists();
        return ResponseEntity.ok(results);
    }
    @DeleteMapping("artists/{id}/delete")
    public ResponseEntity deleteArtist(@PathVariable("id")String id){
        listifyService.deleteArtist(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @PostMapping("save/artist")
    public ResponseEntity saveArtist(@RequestBody ArtistDto artistDto){
        listifyService.saveArtist(artistDto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @GetMapping("artists/random")
    public ResponseEntity getRandomArtist(){
        return ResponseEntity.ok(listifyService.getRandomArtist());
    }
    //Albums
    //TODO update album, delete album
    @GetMapping("albums/{id}")
    public ResponseEntity getAlbumById(@PathVariable("id") String id){
        var result = listifyService.getAlbumById(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("albums/get")
    public ResponseEntity getAllAlbums(){
        var result = listifyService.getAlbums();
        return ResponseEntity.ok(result);
    }
    @PostMapping("save/album")
    public ResponseEntity saveAlbum(@RequestBody AlbumDto albumDto){
        listifyService.saveAlbum(albumDto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("albums/{id}/delete")
    public ResponseEntity deleteAlbum(@PathVariable("id")String id){
        listifyService.deleteAlbum(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
