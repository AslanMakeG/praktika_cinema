package com.praktika.cinema.service;

import com.praktika.cinema.entity.FilmEntity;
import com.praktika.cinema.exception.genre.GenreNotFoundException;
import com.praktika.cinema.model.FilmModel;
import com.praktika.cinema.exception.film.FilmNotFoundException;
import com.praktika.cinema.repository.FilmRepo;
import com.praktika.cinema.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FilmService {
    @Autowired
    private FilmRepo filmRepo;
    @Autowired
    private GenreRepo genreRepo;

    public FilmModel create(FilmEntity film){
        return FilmModel.toModel(filmRepo.save(film));
    }

    public Long delete(Long id){
        filmRepo.deleteById(id);
        return id;
    }

    public FilmModel update(FilmEntity film){
        return FilmModel.toModel(filmRepo.save(film));
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

    public Set<FilmModel> getAllWithGenres(List<Long> ids){
        List<FilmEntity> filmEntities = filmRepo.findAll().stream().filter(
                film -> film.getGenres().stream().anyMatch(
                        element -> ids.contains(element.getId()))).toList();

        Set<FilmModel> films = new HashSet<>();

        filmEntities.forEach(filmEntity -> {
            films.add(FilmModel.toModel(filmEntity));
        });

        return films;
    }

    public FilmModel addGenre(Long filmId, Long genreId) throws FilmNotFoundException, GenreNotFoundException {
        FilmEntity film = filmRepo.findById(filmId).orElseThrow(
                () -> new FilmNotFoundException("Фильм не найден")
        );

        film.addGenre(genreRepo.findById(genreId).orElseThrow(
                () -> new GenreNotFoundException("Жанр не найден")
        ));

        return FilmModel.toModel(filmRepo.save(film));
    }
}
