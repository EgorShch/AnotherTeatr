package com.example.AnotherTEATP.services;

import com.example.AnotherTEATP.models.Hall;
import com.example.AnotherTEATP.repositories.HallRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HallService {

    public final HallRepository hallRepository;

    public List<Hall> halls(){
        return hallRepository.findAll();
    }

    public Hall getHall(int id){
        return hallRepository.findById(id).orElse(null);
    }

    public void saveHall(Hall hall){
        hallRepository.save(hall);
    }

    public void deleteHall(int id){
        hallRepository.deleteById(id);
    }

}
