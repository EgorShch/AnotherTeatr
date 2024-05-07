package com.example.AnotherTEATP.repositories;

import com.example.AnotherTEATP.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findByIdForFilm(int IdForFilm);
}
