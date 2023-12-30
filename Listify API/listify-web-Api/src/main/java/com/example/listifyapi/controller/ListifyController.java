package com.example.listifyapi.controller;

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
    //TODO save track, update track
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
    //Artists
    //TODO save artist, update artist
    @GetMapping("artists/get")
    public ResponseEntity getAllArtists(){
        var results = listifyService.getAllArtists();
        return ResponseEntity.ok(results);
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
    //Albums
    //TODO save album, update album, delete album
    @GetMapping("albums/{id}")
    public ResponseEntity getAlbumById(@PathVariable("id") String id){
        var result = listifyService.getAlbumById(id);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("albums/{id}/delete")
    public ResponseEntity deleteAlbum(@PathVariable("id")String id){
        listifyService.deleteAlbum(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}