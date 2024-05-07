package com.example.AnotherTEATP.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "age_rating")
    int ageRating;

    @Column(name = "release_date")
    LocalDate releaseDate;

    String description;

    int duration;

    String genre;

    @Column(name = "film_name")
    String name;

    String producer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "film")
    Image image;

    int previewImageId;

}
