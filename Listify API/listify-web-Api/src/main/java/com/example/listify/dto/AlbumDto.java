package com.example.listify.dto;

import lombok.Data;


@Data
public class AlbumDto {
    private long id;
    private String spotifyId;
    private String name;
    private String albumType;
    private int totalTracks;
    private String releaseDate;
    private String genres;
}
