package com.example.listify.repositories;

import com.example.listify.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Long, Album> {
}
