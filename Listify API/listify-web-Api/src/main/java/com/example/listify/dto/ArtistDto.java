package com.example.listify.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArtistDto {
    private String spotifyId;
    private String name;
    private List<String> genres;
    private String image;
}
