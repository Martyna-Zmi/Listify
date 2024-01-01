package com.example.listifyapi.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class TrackDto {
    private String spotifyId;
    private String name;
    int durationMs;
    boolean isExplicit;
    private String spotifyUrl;
    private String image;
    private List<String> artists;
    private String album;
    private int popularity;
    public String getPrettyArtists(){
        return Arrays.toString(artists.toArray()).replace("[","").replace("]","");
    }
    public String getExplicitPretty(){
        if(isExplicit){
            return "⛔";
        }
        else return "\uD83C\uDF3C";
    }
    public String getLength(){
        int seconds = (int) (durationMs / 1000) % 60 ;
        int minutes = (int) ((durationMs / (1000*60)) % 60);
        return (minutes+":"+seconds);
    }
}
