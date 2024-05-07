package com.example.AnotherTEATP.services;

import com.example.AnotherTEATP.models.Place;
import com.example.AnotherTEATP.repositories.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaceService {
    public final PlaceRepository placeRepository;

    public List<Place> places(){
        return placeRepository.findAll();
    }

    public Place getPlace(int id){
        return placeRepository.findById(id).orElse(null);
    }

    public void savePlace(Place place){
        placeRepository.save(place);
    }

    public List<Place> placeByHall(int hallId){
        return placeRepository.findByHallId(hallId);
    }

    public void deletePlace(int id){
        placeRepository.deleteById(id);
    }

}
