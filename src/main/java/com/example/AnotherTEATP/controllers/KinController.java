package com.example.AnotherTEATP.controllers;

import com.example.AnotherTEATP.models.Film;
import com.example.AnotherTEATP.models.Hall;
import com.example.AnotherTEATP.models.Image;
import com.example.AnotherTEATP.models.Seance;
import com.example.AnotherTEATP.repositories.ImageRepository;
import com.example.AnotherTEATP.services.FilmService;
import com.example.AnotherTEATP.services.HallService;
import com.example.AnotherTEATP.services.SeanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class KinController {

    private final SeanceService seanceService;
    private final FilmService filmService;
    private final HallService hallService;
    private final ImageRepository imageRepository;

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

    @GetMapping("/create")
    public String crPhoto(Model model){
        model.addAttribute("images", imageRepository.findAll());
        return "products/createfilm";
    }

    @PostMapping("/create")
    public String photo(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Post");
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        imageRepository.save(image);
        return "redirect:/products/createfilm";
    }

}
