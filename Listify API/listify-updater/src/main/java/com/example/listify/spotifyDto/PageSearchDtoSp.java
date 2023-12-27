package com.example.listify.spotifyDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageSearchDtoSp {
    @JsonProperty("items")
    private List<SavedTrackDtoSp> savedTracks;
    @JsonProperty("total")
    private int tracksFound;

}
