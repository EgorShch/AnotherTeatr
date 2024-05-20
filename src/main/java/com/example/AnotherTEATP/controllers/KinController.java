package com.example.AnotherTEATP.controllers;

import com.example.AnotherTEATP.models.*;
import com.example.AnotherTEATP.repositories.ImageRepository;
import com.example.AnotherTEATP.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/seances")
    public String seances(Model model){
        List<Seance> s = seanceService.seances();
        model.addAttribute("seances", s);
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

    @GetMapping("/create")
    public String crPhoto(Model model){
        model.addAttribute("images", imageRepository.findAll());
        return "products/createfilm";
    }

    @PostMapping("/create")
    public String photo(@RequestParam("file") MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        imageRepository.save(image);
        return "redirect:/products/seances";
    }

    @GetMapping("/hall/{seanceId}")
    public String hall1(@PathVariable int seanceId, Model model){
        List<Integer> occupiedSeats = ticketService.getOccupiedSeats(seanceId);
        int hallId = seanceService.getHallIdById(seanceId);
        List<Place> places = placeService.placeByTickets(occupiedSeats, hallId);
        model.addAttribute("places", places);
        model.addAttribute("seanceId", seanceId);
        model.addAttribute("date", seanceService.getSeance(seanceId).getDate());
        return "products/hall";
    }

    @PostMapping("/saveTickets")
    public String saveTickets(@RequestBody List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticketService.saveTicket(ticket);
        }
        return "redirect:/products/hall/" + tickets.get(0).getSeanceId();
    }

}
