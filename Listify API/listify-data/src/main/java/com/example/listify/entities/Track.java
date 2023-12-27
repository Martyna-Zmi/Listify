package com.example.listify.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String addedAt;
    private String spotifyId;
    private String name;
    int durationMs;
    boolean isExplicit; //uwaga na get i set
    private String spotifyUrl;
    private int trackNumberOnAlbum;
    @ManyToOne(cascade = CascadeType.ALL)
    private Album album;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Artist> artists;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getDurationMs() {
        return durationMs;
    }
    public int getTrackNumberOnAlbum() {
        return trackNumberOnAlbum;
    }
    public String getAddedAt() {
        return addedAt;
    }
    public String getSpotifyId() {
        return spotifyId;
    }
    public String getSpotifyUrl() {
        return spotifyUrl;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }
    public void setDurationMs(int durationMs) {
        this.durationMs = durationMs;
    }
    public void setExplicit(boolean explicit) {
        isExplicit = explicit;
    }
    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }
    public void setSpotifyUrl(String spotifyUrl) {
        this.spotifyUrl = spotifyUrl;
    }
    public void setTrackNumberOnAlbum(int trackNumberOnAlbum) {
        this.trackNumberOnAlbum = trackNumberOnAlbum;
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
}
