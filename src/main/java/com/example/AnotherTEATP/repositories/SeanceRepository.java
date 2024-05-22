package com.example.AnotherTEATP.repositories;

import com.example.AnotherTEATP.models.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {
    List<Seance> findSeancesByDate(LocalDate localDate);
}
