package com.example.AnotherTEATP.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "date_of_sale")
    LocalDate date;

    @Column(name = "place_id")
    int placeId;

    @Column(name = "seance_id")
    int seanceId;
}
