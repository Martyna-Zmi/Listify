package com.example.listify.repositories;

import com.example.listify.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Long, Track> {
}
