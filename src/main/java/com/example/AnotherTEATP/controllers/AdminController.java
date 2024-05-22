package com.example.AnotherTEATP.controllers;

import com.example.AnotherTEATP.models.Film;
import com.example.AnotherTEATP.models.Image;
import com.example.AnotherTEATP.models.Seance;
import com.example.AnotherTEATP.repositories.ImageRepository;
import com.example.AnotherTEATP.services.FilmService;
import com.example.AnotherTEATP.services.SeanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private final ImageRepository imageRepository;
    private final FilmService filmService;
    private final SeanceService seanceService;

    @GetMapping()
    public String admin(){
        return "hello";
    }


    @GetMapping("/create")
    public String film(){
        return "products/createfilm";
    }

    @PostMapping("/create-film")
    public String saveFilm(Film film, @RequestParam("file") MultipartFile file) throws IOException {
        filmService.saveFilm(film, file);
        return "redirect:/admin/create";
    }

    @PostMapping("/create-seance")
    public String saveSeance(Seance seance){
        seanceService.saveSeance(seance);
        return "redirect:/admin/create";
    }
}
