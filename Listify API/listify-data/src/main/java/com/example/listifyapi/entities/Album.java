package com.example.listifyapi.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Album {
    @Id
    @Column(name = "Album_id")
    private String spotifyId;
    private String name;
    private String albumType;
    private int totalTracks;
    private String releaseDate;
    private String image;
    @OneToMany(mappedBy = "album")
    private List<Track> tracks;

    public void addTrack(Track track){
        this.tracks.add(track);
    }
    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAlbumType(String albumType) {
        this.albumType = albumType;
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
    public String getName() {
        return name;
    }
    public int getTotalTracks() {
        return totalTracks;
    }
    public String getAlbumType() {
        return albumType;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
    public List<Track> getTracks() {
        return tracks;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
