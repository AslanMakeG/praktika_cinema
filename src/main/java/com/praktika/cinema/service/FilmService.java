package com.praktika.cinema.service;

import com.praktika.cinema.entity.FilmEntity;
import com.praktika.cinema.model.FilmModel;
import com.praktika.cinema.exception.film.FilmNotFoundException;
import com.praktika.cinema.repository.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    @Autowired
    private FilmRepo filmRepo;

    public FilmModel create(FilmEntity film){
        return FilmModel.toModel(filmRepo.save(film));
    }

    public Long delete(Long id){
        filmRepo.deleteById(id);
        return id;
    }

    public FilmModel getOne(Long id) throws FilmNotFoundException {
        return FilmModel.toModel(filmRepo.findById(id).orElseThrow(
                () -> new FilmNotFoundException("Фильм не найден")
        ));
    }

    public List<FilmModel> getAll(){
        List<FilmModel> films = new ArrayList<>();
        for(FilmEntity film : filmRepo.findAll()){
            films.add(FilmModel.toModel(film));
        }
        return films;
    }
}
