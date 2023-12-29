package com.example.listify.repositories;

import com.example.listify.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> getArtistBySpotifyId(String spotifyId);
}
