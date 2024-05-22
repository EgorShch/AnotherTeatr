package com.example.AnotherTEATP.controllers;

import com.example.AnotherTEATP.models.*;
import com.example.AnotherTEATP.repositories.ImageRepository;
import com.example.AnotherTEATP.services.*;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class KinController {

    private final SeanceService seanceService;
    private final FilmService filmService;
    private final HallService hallService;
    private final ImageRepository imageRepository;
    private final TicketService ticketService;
    private final PlaceService placeService;
    private final UserService userService;

    @GetMapping("/seances")
    public String seances(@RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model){
        if (date == null) {
            date = LocalDate.now();
        }

        List<Seance> seances = seanceService.getSeancesByDate(date);
        model.addAttribute("selectedDate", date);
        model.addAttribute("seances", seances);
        model.addAttribute("filmService", filmService);
        model.addAttribute("hallService", hallService);
        return "products/index";
    }

    @GetMapping("/films")
    public String films(Model model){
        model.addAttribute("films", filmService.films());
        model.addAttribute("images", imageRepository.findAll());
        return "products/afisha";
    }


    @GetMapping("/films/{id}")
    public String film(@PathVariable int id, Model model){
        Film film = filmService.getFilm(id);
        model.addAttribute("film", film);
        model.addAttribute("img", imageRepository.findById(film.getPreviewImageId()));
        return "products/film";
    }


    @GetMapping("/seances/{seanceId}")
    public String hall1(@PathVariable int seanceId, Model model){
        List<Integer> occupiedSeats = ticketService.getOccupiedSeats(seanceId);
        int hallId = seanceService.getHallIdById(seanceId);
        List<Place> places = placeService.placeByTickets(occupiedSeats, hallId);
        Map<Integer, List<Place>> placesByColumn = places.stream().collect(Collectors.groupingBy(Place::getColumn));

        model.addAttribute("placesByColumn", placesByColumn);
        model.addAttribute("seanceId", seanceId);
        model.addAttribute("date", seanceService.getSeance(seanceId).getDate());
        return "products/hall";
    }

    @PostMapping("/saveTickets")
    public String saveTickets(@RequestBody List<Ticket> tickets, Principal principal) {
        for (Ticket ticket : tickets) {
            ticketService.saveTicket(principal, ticket);
        }
        return "redirect:/products/seances/" + tickets.get(0).getSeanceId();
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("tickets", ticketService.getTicketsByUser(user));
        model.addAttribute("fS", filmService);
        model.addAttribute("pS", placeService);
        model.addAttribute("sS", seanceService);
        model.addAttribute("hS", hallService);
        return "/products/profile";
    }

}
