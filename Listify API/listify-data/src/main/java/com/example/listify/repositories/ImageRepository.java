package com.example.listify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Long, ImageRepository> {
}
