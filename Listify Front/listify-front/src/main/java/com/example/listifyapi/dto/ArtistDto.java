package com.example.listifyapi.dto;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

@Data
public class ArtistDto {
    private String spotifyId;
    private String name;
    private String genres;
    private String image;
    private int popularity;
    public String popularityStats(){
        if(popularity<30){
            return "Who?";
        }
        else if(popularity<60){
            return "Somewhat recognizable";
        }
        else if(popularity<75){
            return "A twinkling star";
        }
        else{
            return "A superstar";
        }
    }
}
