package com.example.listify.spotifyDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SavedTrackDtoSp {
    @JsonProperty("added_at")
    private String addedAt;
    @JsonProperty("track")
    private TrackDtoSp track;
}
