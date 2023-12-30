package com.example.listifyapi.dto;

import lombok.Data;

@Data
public class TrackDto {
    private String spotifyId;
    private String name;
    int durationMs;
    boolean isExplicit;
    private String spotifyUrl;
}