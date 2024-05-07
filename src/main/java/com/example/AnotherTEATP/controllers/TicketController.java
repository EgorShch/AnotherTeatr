package com.example.AnotherTEATP.controllers;


import com.example.AnotherTEATP.models.Ticket;
import com.example.AnotherTEATP.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping()
    public List<Ticket> tickets(){
        return ticketService.tickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable int id){
        return ticketService.getTicket(id);
    }

    @PostMapping()
    public String saveTicket(@RequestBody Ticket ticket){
        ticketService.saveTicket(ticket);
        return "Готово";
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable int id){
        ticketService.deleteTicket(id);
        return "Готово";
    }


}
