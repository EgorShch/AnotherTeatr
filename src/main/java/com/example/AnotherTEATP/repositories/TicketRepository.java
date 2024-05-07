package com.example.AnotherTEATP.repositories;

import com.example.AnotherTEATP.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
