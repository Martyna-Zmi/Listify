package com.example.listify.entities;

import com.example.listify.annotations.ListToString;
import jakarta.persistence.*;


import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String spotifyId;
    private String name;
    private String albumType;
    private int totalTracks;
    private String releaseDate;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Artist> artists;
    @ListToString
    private String genres;
   @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images;

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setTotalTracks(int totalTracks) {
        this.totalTracks = totalTracks;
    }
    public String getSpotifyId() {
        return spotifyId;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getTotalTracks() {
        return totalTracks;
    }
    public List<Artist> getArtists() {
        return artists;
    }
    public List<Image> getImages() {
        return images;
    }
    public String getAlbumType() {
        return albumType;
    }
    public String getGenres() {
        return genres;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
}
