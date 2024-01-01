package com.example.listifyapi.controller;

import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("listify/")
public class ThymeController {
    private FrontService frontService;
    @Autowired
    public ThymeController(FrontService frontService){
        this.frontService = frontService;
    }
    @GetMapping("viewTracks")
    public String viewAllTracks(Model model){
        model.addAttribute("tracks", frontService.findAllTracks());
        return "viewTracks";
    }
    @GetMapping("main")
    public String mainPage(Model model){
        model.addAttribute("randomArtist", frontService.randomArtist());
        model.addAttribute("randomSong", frontService.randomTrack());
        return "index";
    }
    @GetMapping("tracks/{id}")
    public String trackById(@PathVariable("id")String id, Model model){
        model.addAttribute("track", frontService.trackById(id));
        return "viewTrack";
    }
    @GetMapping("artists/{id}")
    public String artistById(@PathVariable("id")String id, Model model){
        model.addAttribute("artist", frontService.artistById(id));
        return "viewArtist";
    }
    @GetMapping(value = "artists/save")
    public String addArtistInfo(Model model){
        model.addAttribute("artist", new ArtistDto());
        return "saveArtist";
    }
//    @RequestMapping (value = "artists/save", method = RequestMethod.POST)
//    public String addArtist(@ModelAttribute ArtistDto artistDto, Model model){
//        frontService.saveArtist(artistDto);
//        return "index";
//    }
}
