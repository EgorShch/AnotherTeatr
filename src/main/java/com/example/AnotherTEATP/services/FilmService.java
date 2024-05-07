package com.example.AnotherTEATP.services;

import com.example.AnotherTEATP.models.Film;
import com.example.AnotherTEATP.models.Image;
import com.example.AnotherTEATP.repositories.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FilmService
{
    public final FilmRepository filmRepository;

    public List<Film> films(){
        return filmRepository.findAll();
    }

    public Film getFilm(int id){
        return filmRepository.findById(id).orElse(null);
    }

    public void saveFilm(Film film, MultipartFile file) throws IOException {
        Image image = null;

        if (file.getSize() != 0) {
            image = toImageEntity(file);
        }
        film.setImage(image);
        Film filmFromDb = filmRepository.save(film);
        filmFromDb.setPreviewImageId(filmFromDb.getImage().getId());
        filmRepository.save(film);
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteFilm(int id){
        filmRepository.deleteById(id);
    }

}
