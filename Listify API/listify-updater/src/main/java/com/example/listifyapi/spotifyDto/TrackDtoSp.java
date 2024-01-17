package com.example.listifyapi.spotifyDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TrackDtoSp {
    @JsonProperty("id")
    private String spotifyId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("duration_ms")
    private int durationMs;
    @JsonProperty("popularity")
    private int popularity;
    @JsonProperty("explicit")
    boolean isExplicit;
    @JsonProperty("uri")
    private String spotifyUrl;
    @JsonProperty("album")
    private AlbumDtoSp album;
    @JsonProperty("track_number")
    private int trackNumberOnAlbum;
    @JsonProperty("artists")
    private List<ArtistDtoSp> artists = new ArrayList<>();
}
