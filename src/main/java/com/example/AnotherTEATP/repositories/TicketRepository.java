package com.example.AnotherTEATP.repositories;

import com.example.AnotherTEATP.models.Ticket;
import com.example.AnotherTEATP.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findBySeanceId(int seanceId);
    List<Ticket> findByUser(User user);
}
