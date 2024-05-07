package com.example.AnotherTEATP.controllers;


import com.example.AnotherTEATP.models.Seance;
import com.example.AnotherTEATP.services.SeanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seances")
@AllArgsConstructor
public class SeanceController {

    private final SeanceService seanceService;

    @GetMapping()
    public List<Seance> seances(){
        return seanceService.seances();
    }

    @GetMapping("/{id}")
    public Seance getSeance(@PathVariable() int id){
        return seanceService.getSeance(id);
    }

    @PostMapping()
    public String saveSeance(@RequestBody Seance seance){
        seanceService.saveSeance(seance);
        return "Готово";
    }

    @DeleteMapping("/{id}")
    public String deleteSeance(@PathVariable() int id){
        seanceService.deleteSeance(id);
        return "Готово";
    }

}
