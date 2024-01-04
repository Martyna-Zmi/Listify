package com.example.listifyapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class TrackDto {
    private String spotifyId;
    private String name;
    int durationMs;
    boolean isExplicit;
    //private String spotifyUrl;
    private String image;
    private List<String> artists;
    private String tempArtist;
    private String album;
    private int popularity;
}
