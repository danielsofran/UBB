package com.example.mobilebackend1.repository;

import com.example.mobilebackend1.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    public Optional<Image> findByUuid(String uuid);
}

