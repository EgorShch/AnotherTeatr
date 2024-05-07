package com.example.AnotherTEATP.repositories;

import com.example.AnotherTEATP.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findByHallId(int hallId);
}
