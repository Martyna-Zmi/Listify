package com.example.listifyapi.controller;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import com.example.listifyapi.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @GetMapping("error")
    public String error(RedirectAttributes redirectAttributes){
        return "errorPage";
    }
    //Tracks
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
    public String addTrack(@ModelAttribute TrackDto track, Model model, BindingResult result) {
        frontService.saveTrack(track);
        return ("redirect:/listify/tracks/"+track.getSpotifyId());
    }
    @GetMapping("viewTracks")
    public String viewAllTracks(Model model){
        model.addAttribute("tracks", frontService.findAllTracks());
        return "viewTracks";
    }
    @GetMapping("update/track/{id}")
    public String updateTrackInfo(Model model, @PathVariable String id){
        model.addAttribute("track", frontService.trackById(id));
        return "updateTrack";
    }
    @RequestMapping(value = "update/track/{id}", method = RequestMethod.POST)
    public String updateTrack(Model model, @ModelAttribute TrackDto track, @PathVariable String id){
        frontService.updateTrack(track);
        return ("redirect:/listify/tracks/"+id);
    }
    @GetMapping("tracks/{id}/delete")
    public String deleteTrackInfo(Model model, @PathVariable String id){
        model.addAttribute("track", frontService.trackById(id));
        return "deleteTrack";
    }
    @RequestMapping(value = "tracks/{id}/delete", method = RequestMethod.POST)
    public String deleteTrack(Model model, @PathVariable String id){
        frontService.deleteTrack(id);
        return "redirect:/listify/main";
    }
    //Albums
    @GetMapping("albums/{id}")
    public String albumById(@PathVariable("id")String id, Model model){
        var album = frontService.getAlbumById(id);
        model.addAttribute("album", album);
        model.addAttribute("tracks", frontService.albumsTracks(album));
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
    @GetMapping(value = "update/album/{id}")
    public String updateAlbumInfo(Model model,@PathVariable("id")String id){
        model.addAttribute("album", frontService.getAlbumById(id));
        return "updateAlbum";
    }
    @RequestMapping (value = "update/album/{id}", method = RequestMethod.POST)
    public String updateAlbum(@ModelAttribute AlbumDto album, Model model, @PathVariable String id){
        frontService.updateAlbum(album);
        return ("redirect:/listify/albums/"+id);
    }
    @GetMapping(value = "albums/{id}/delete")
    public String deleteAlbumInfo(Model model,@PathVariable("id")String id){
        var album = frontService.getAlbumById(id);
        model.addAttribute("album", album);
        model.addAttribute("tracks", frontService.albumsTracks(album));
        return "deleteAlbum";
    }
    @RequestMapping (value = "albums/{id}/delete", method = RequestMethod.POST)
    public String deleteAlbum(Model model, @PathVariable String id){
        frontService.deleteAlbum(id);
        return "redirect:/listify/main";
    }
    //Artists
    @GetMapping("artists/{id}")
    public String artistById(@PathVariable("id")String id, Model model){
        model.addAttribute("artist", frontService.artistById(id));
        return "viewArtist";
    }
    @GetMapping("viewArtists")
    public String viewArtists(Model model){
        model.addAttribute("artists", frontService.getAllArtists());
        return "viewArtists";
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
    @GetMapping("update/artist/{id}")
    public String updateArtistInfo(Model model, @PathVariable("id")String id){
        model.addAttribute("artist", frontService.artistById(id));
        return "updateArtist";
    }
    @RequestMapping (value = "update/artist/{id}", method = RequestMethod.POST)
    public String updateArtist(@ModelAttribute ArtistDto artist, Model model){
        frontService.updateArtist(artist);
        return ("redirect:/listify/artists/"+artist.getSpotifyId());
    }
    @GetMapping("artists/{id}/delete")
    public String deleteArtistInfo(Model model, @PathVariable("id")String id){
        model.addAttribute("artist", frontService.artistById(id));
        return "deleteArtist";
    }
    @RequestMapping (value = "artists/{id}/delete", method = RequestMethod.POST)
    public String deleteArtist(@ModelAttribute ArtistDto artist, Model model, @PathVariable String id){
        frontService.deleteArtist(id);
        return "redirect:/listify/main";
    }
}