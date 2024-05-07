package com.example.AnotherTEATP.repositories;

import com.example.AnotherTEATP.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
