package com.example.AnotherTEATP.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "seance")
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cost;

    LocalDate date;

    @Column(name = "film_id")
    int filmId;

    @Column(name = "hall_id")
    int hallId;

    LocalTime time;
}
