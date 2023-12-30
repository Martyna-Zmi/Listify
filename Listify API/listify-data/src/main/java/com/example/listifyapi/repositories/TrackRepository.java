package com.example.listifyapi.repositories;

import com.example.listifyapi.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public interface TrackRepository extends JpaRepository<Track, String> {
    Optional<Track> getTrackBySpotifyId(String id);
    List<Track> searchAllByPopularityGreaterThanEqual(int popularity);
}
