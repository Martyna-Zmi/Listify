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
    String explicit;
    private String image;
    private List<String> artists;
    private String tempArtist;
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
        String secondsString = String.valueOf(seconds);
        if(secondsString.length()==1){
            secondsString+="0";
        }
        return (minutes+":"+secondsString);
    }
    public String popularityStats(){
        if(popularity<30){
            return "Rarely heard of";
        }
        else if(popularity<50){
            return "Somewhat recognizable";
        }
        else if(popularity<60){
            return "I've heard it somewhere!";
        }
        else if(popularity<75){
            return "A widely recognised tune";
        }
        else{
            return "An absolute banger";
        }
    }
    public void explicitFromString(){
        this.isExplicit = !this.explicit.equals("false");
    }
}
