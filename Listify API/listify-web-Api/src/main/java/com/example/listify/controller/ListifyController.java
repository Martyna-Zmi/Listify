package com.example.listify.controller;

import com.example.listify.service.ListifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RequestMapping("listify/")
@RestController
public class ListifyController {
    private final ListifyService listifyService;
    @Autowired
    public ListifyController(ListifyService listifyService){
        this.listifyService = listifyService;
    }
   //Tracks
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
    //Artists
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
    //Albums
}
