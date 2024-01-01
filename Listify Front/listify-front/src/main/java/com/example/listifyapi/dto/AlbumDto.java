package com.example.listifyapi.dto;

import lombok.Data;


@Data
public class AlbumDto {
    private String spotifyId;
    private String name;
    private String albumType;
    private int totalTracks;
    private String releaseDate;
    private String image;
}
