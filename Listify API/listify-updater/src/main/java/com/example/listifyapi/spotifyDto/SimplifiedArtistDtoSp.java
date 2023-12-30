package com.example.listifyapi.spotifyDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SimplifiedArtistDtoSp {
    @JsonProperty("id")
    private String spotify_id;
    @JsonProperty("name")
    private String name;
}
