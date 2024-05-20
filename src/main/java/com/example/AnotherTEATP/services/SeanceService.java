package com.example.AnotherTEATP.services;

import com.example.AnotherTEATP.models.Seance;
import com.example.AnotherTEATP.repositories.SeanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeanceService {

    public final SeanceRepository seanceRepository;

    public List<Seance> seances(){
        return seanceRepository.findAll();
    }

    public Seance getSeance(int id){
        return seanceRepository.findById(id).orElse(null);
    }

    public void saveSeance(Seance seance){
        seanceRepository.save(seance);
    }

    public void deleteSeance(int id){
        seanceRepository.deleteById(id);
    }

    public int getHallIdById(int id){
        return getSeance(id).getHallId();
    }

}
