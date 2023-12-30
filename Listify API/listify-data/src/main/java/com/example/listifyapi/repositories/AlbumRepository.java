package com.example.listifyapi.repositories;

import com.example.listifyapi.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {
    Optional<Album> findBySpotifyId(String id);
}
