package com.example.listifyapi.controller;

import com.example.listifyapi.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
