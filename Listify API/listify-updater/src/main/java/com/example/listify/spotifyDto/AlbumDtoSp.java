package com.example.listify.spotifyDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AlbumDtoSp {
    @JsonProperty("id")
    private String spotifyId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("album_type")
    private String albumType;
    @JsonProperty("total_tracks")
    private int totalTracks;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("artists")
    private List<ArtistDtoSp> artists;
    @JsonProperty("genres")
    private List<String> genres;
    @JsonProperty("images")
    private List<ImageDtoSp> images;
}
