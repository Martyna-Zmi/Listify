package com.example.listifyapi.controller;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import com.example.listifyapi.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("listify/")
public class ThymeController {
    private final FrontService frontService;
    @Autowired
    public ThymeController(FrontService frontService){
        this.frontService = frontService;
    }
    @GetMapping("main")
    public String mainPage(Model model){
        model.addAttribute("randomArtist", frontService.randomArtist());
        model.addAttribute("randomSong", frontService.randomTrack());
        return "index";
    }
    //Tracks TODO update, delete
    @GetMapping("tracks/{id}")
    public String trackById(@PathVariable("id")String id, Model model){
        model.addAttribute("track", frontService.trackById(id));
        return "viewTrack";
    }
    @GetMapping(value = "save/track")
    public String addTrackInfo(Model model){
        model.addAttribute("track", frontService.emptyTrack());
        model.addAttribute("albums", frontService.getAllAlbums());
        model.addAttribute("artists", frontService.getAllArtists());
        return "saveTrack";
    }
    @RequestMapping (value = "save/track", method = RequestMethod.POST)
    public String addTrack(@ModelAttribute TrackDto track, Model model){
        frontService.saveTrack(track);
        return ("redirect:/listify/tracks/"+track.getSpotifyId());
    }
    @GetMapping("viewTracks")
    public String viewAllTracks(Model model){
        model.addAttribute("tracks", frontService.findAllTracks());
        return "viewTracks";
    }
    //Albums TODO update, delete, show all
    @GetMapping("albums/{id}")
    public String albumById(@PathVariable("id")String id, Model model){
        model.addAttribute("album", frontService.getAlbumById(id));
        model.addAttribute("tracks", frontService.albumsTracks(frontService.getAlbumById(id)));
        return "viewAlbum";
    }
    @GetMapping("viewAlbums")
        public String allAlbums(Model model){
            model.addAttribute("albums", frontService.getAllAlbums());
            return "viewAlbums";
        }

    @GetMapping(value = "save/album")
    public String addAlbumInfo(Model model){
        model.addAttribute("album", frontService.emptyAlbum());
        return "saveAlbum";
    }
    @RequestMapping (value = "save/album", method = RequestMethod.POST)
    public String addAlbum(@ModelAttribute AlbumDto album, Model model){
        frontService.saveAlbum(album);
        return ("redirect:/listify/albums/"+album.getSpotifyId());
    }
    //Artists TODO update, delete, show all
    @GetMapping("artists/{id}")
    public String artistById(@PathVariable("id")String id, Model model){
        model.addAttribute("artist", frontService.artistById(id));
        return "viewArtist";
    }
    @GetMapping(value = "save/artist")
    public String addArtistInfo(Model model){
        model.addAttribute("artist", frontService.emptyArtist());
        return "saveArtist";
    }
    @RequestMapping (value = "save/artist", method = RequestMethod.POST)
    public String addArtist(@ModelAttribute ArtistDto artist, Model model){
        frontService.saveArtist(artist);
        return ("redirect:/listify/artists/"+artist.getSpotifyId());
    }
}