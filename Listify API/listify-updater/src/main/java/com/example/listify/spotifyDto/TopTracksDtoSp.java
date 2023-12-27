package com.example.listify.spotifyDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TopTracksDtoSp {
    @JsonProperty("tracks")
    private List<TrackDtoSp> tracks;

}
