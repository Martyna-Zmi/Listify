package com.example.listifyapi.spotifyDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ArtistDtoSp {
    @JsonProperty("id")
    private String spotifyId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("genres")
    private String[] genres;
    @JsonProperty("images")
    private List<ImageDtoSp> image = new ArrayList<>();
    @JsonProperty("popularity")
    private int popularity;
}
