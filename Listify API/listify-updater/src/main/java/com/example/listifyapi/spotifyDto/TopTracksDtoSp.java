package com.example.listifyapi.spotifyDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class TopTracksDtoSp {
    @JsonProperty("tracks")
    private ArrayList<TrackDtoSp> tracks = new ArrayList<>();

}
