package com.example.listify.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artist {
    @Id
    @Column(name = "Artist_id")
    private String spotifyId;
    private String name;
    private String genres;
    private String image;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "track_artist",
            joinColumns = { @JoinColumn(name = "JT1_artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "JT1_track_id") })
    private List<Track> tracks;

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public List<Track> getTracks() {
        return tracks;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
