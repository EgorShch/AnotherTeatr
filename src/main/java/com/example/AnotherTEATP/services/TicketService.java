package com.example.AnotherTEATP.services;

import com.example.AnotherTEATP.models.Place;
import com.example.AnotherTEATP.models.Ticket;
import com.example.AnotherTEATP.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    public final TicketRepository ticketRepository;

    public List<Ticket> tickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicket(int id){
        return ticketRepository.findById(id).orElse(null);
    }

    public void saveTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void deleteTicket(int id){
        ticketRepository.deleteById(id);
    }

    public List<Integer> getOccupiedSeats (int seanceId){
        List<Ticket> tickets = ticketRepository.findBySeanceId(seanceId);
        List<Integer> occupiedSeats = new ArrayList<>();
        for (Ticket ticket : tickets){
            occupiedSeats.add(ticket.getPlaceId());
        }
        return occupiedSeats;
    }

}
