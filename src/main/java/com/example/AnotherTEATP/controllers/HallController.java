package com.example.AnotherTEATP.controllers;


import com.example.AnotherTEATP.models.Hall;
import com.example.AnotherTEATP.services.HallService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/halls")
@AllArgsConstructor
public class HallController {

    private final HallService hallService;

    @GetMapping()
    public List<Hall> halls(){
        return hallService.halls();
    }

    @GetMapping("/{id}")
    public Hall getHall(@PathVariable() int id){
        return hallService.getHall(id);
    }

    @PostMapping()
    public String saveHall(@RequestBody Hall hall){
        hallService.saveHall(hall);
        return "Готово";
    }

    @DeleteMapping("/{id}")
    public String deleteHall(@PathVariable() int id){
        hallService.deleteHall(id);
        return "Готово";
    }

}
