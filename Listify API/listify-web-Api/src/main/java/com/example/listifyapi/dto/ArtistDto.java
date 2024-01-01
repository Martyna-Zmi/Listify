package com.example.listifyapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArtistDto {
    private String spotifyId;
    private String name;
    private String genres;
    private String image;
    private int popularity;
}
