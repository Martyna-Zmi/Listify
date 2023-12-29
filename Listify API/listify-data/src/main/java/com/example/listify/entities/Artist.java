package com.example.listify.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String spotifyId;
    private String name;
   // @ListToString
    private String genres;
    //@ImageToURL
    private String image;
    //@CustomMapping
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "album_artist",
            joinColumns = { @JoinColumn(name = "Artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "Album_id") })
    private List<Album> albums;
    //@CustomMapping
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "track_artist",
            joinColumns = { @JoinColumn(name = "Artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "track_id") })
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
    public void setId(long id) {
        this.id = id;
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
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
    public List<Album> getAlbums() {
        return albums;
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
