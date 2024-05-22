package com.example.AnotherTEATP.services;

import com.example.AnotherTEATP.models.Place;
import com.example.AnotherTEATP.models.Ticket;
import com.example.AnotherTEATP.models.User;
import com.example.AnotherTEATP.repositories.TicketRepository;
import com.example.AnotherTEATP.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    public final TicketRepository ticketRepository;
    public final UserRepository userRepository;

    public List<Ticket> tickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicket(int id){
        return ticketRepository.findById(id).orElse(null);
    }

    public void saveTicket(Principal principal, Ticket ticket){
        ticket.setUser(getUserByPrincipal(principal));
        ticketRepository.save(ticket);
    }

    public User getUserByPrincipal(Principal principal){
        if (principal == null){
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }

    public List<Ticket> getTicketsByUser(User user){
        return ticketRepository.findByUser(user);
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
