package com.example.AnotherTEATP.controllers;


import com.example.AnotherTEATP.models.Place;
import com.example.AnotherTEATP.services.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/places")
@AllArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping()
    public List<Place> places(){
        return placeService.places();
    }

    @GetMapping("/{id}")
    public Place getPlace(@PathVariable() int id){
        return placeService.getPlace(id);
    }

    @GetMapping("/hall/{id}")
    public List<Place> placesByHall(@PathVariable() int id){
        return placeService.placeByHall(id);
    }

    @PostMapping()
    public String savePlace(@RequestBody Place place){
        placeService.savePlace(place);
        return "Готово";
    }

    @DeleteMapping("/{id}")
    public String deletePlace(@PathVariable() int id){
        placeService.deletePlace(id);
        return "Готово";
    }

}
