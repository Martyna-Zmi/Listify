package com.example.listify.entities;

import com.example.listify.annotations.ListToString;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String spotifyId;
    private String name;
    @ListToString
    private String genres;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Image> images;

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(long id) {
        this.id = id;
    }
    public List<Image> getImages() {
        return images;
    }
    public String getSpotifyId() {
        return spotifyId;
    }
    public String getGenres() {
        return genres;
    }
    public String getName() {
        return name;
    }
    public long getId() {
        return id;
    }
}
