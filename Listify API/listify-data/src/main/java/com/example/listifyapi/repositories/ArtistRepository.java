package com.example.listifyapi.repositories;

import com.example.listifyapi.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {
    Optional<Artist> getArtistBySpotifyId(String spotifyId);
    List<Artist> searchAllByPopularityGreaterThanEqual(int popularity);
    List<Artist> searchAllByGenresContaining(String genre);
}
