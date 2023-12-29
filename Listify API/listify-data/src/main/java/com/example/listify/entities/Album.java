package com.example.listify.entities;

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
    //@CustomMapping
    @ManyToMany(mappedBy = "albums",cascade = CascadeType.ALL)
    private List<Artist> artists;
    //@ImageToURL
    private String image;
    //@CustomMapping
    @OneToMany(mappedBy = "album",cascade = CascadeType.ALL)
    private List<Track> tracks;

    public void addTrack(Track track){
       this.tracks.add(track);
    }
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
