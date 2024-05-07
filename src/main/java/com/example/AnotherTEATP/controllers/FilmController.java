package com.example.AnotherTEATP.controllers;


import com.example.AnotherTEATP.models.Film;
import com.example.AnotherTEATP.services.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
@AllArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping()
    public List<Film> films(){
        return filmService.films();
    }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable int id){
        return filmService.getFilm(id);
    }

    @PostMapping()
    public String saveFilm(Film film, @RequestParam("file") MultipartFile file) throws IOException {
        filmService.saveFilm(film, file);
        return "Готово";
    }

    @DeleteMapping("/{id}")
    public String deleteFilm(@PathVariable int id){
        filmService.deleteFilm(id);
        return "Готово";
    }


}
