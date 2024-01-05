package com.example.listifyapi.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Track {
    @Id
    @Column(name = "Track_id")
    private String spotifyId;
    private String name;
    int durationMs;
    boolean isExplicit;
    private int popularity;
    @ManyToOne
    @JoinColumn(name = "album")
    private Album album;
    @ManyToMany(mappedBy = "tracks",cascade = CascadeType.ALL)
    private List<Artist> artists;

    public String getName() {
        return name;
    }
    public int getDurationMs() {
        return durationMs;
    }
    public String getSpotifyId() {
        return spotifyId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDurationMs(int durationMs) {
        this.durationMs = durationMs;
    }
    public void setIsExplicit(boolean explicit) {
        isExplicit = explicit;
    }
    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }
    public List<Artist> getArtists() {
        return artists;
    }
    public Album getAlbum() {
        return album;
    }
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }
    public boolean isExplicit() {
        return isExplicit;
    }
    public void setExplicit(boolean explicit) {
        isExplicit = explicit;
    }
    public int getPopularity() {
        return popularity;
    }
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
