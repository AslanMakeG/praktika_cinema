package com.praktika.cinema.service;

import com.praktika.cinema.entity.FilmEntity;
import com.praktika.cinema.entity.UserEntity;
import com.praktika.cinema.entity.UserRatingEntity;
import com.praktika.cinema.exception.genre.GenreNotFoundException;
import com.praktika.cinema.exception.person.PersonNotFoundException;
import com.praktika.cinema.model.FilmModel;
import com.praktika.cinema.exception.film.FilmNotFoundException;
import com.praktika.cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmService {
    @Autowired
    private FilmRepo filmRepo;
    @Autowired
    private GenreRepo genreRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserRatingRepo userRatingRepo;

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

    public Set<FilmModel> filter(List<Long> genreIds, List<Long> personIds){
        List<FilmEntity> filmEntities = filmRepo.findAll();



        if(genreIds != null){
            filmEntities = filmEntities.stream().filter(film -> film.getGenres().stream().anyMatch(
                    element -> genreIds.contains(element.getId()))).toList();
        }

        if(personIds != null){
            filmEntities = filmEntities.stream().filter(film -> film.getPersons().stream().anyMatch(
                    element -> personIds.contains(element.getId()))).toList();
        }

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

    public FilmModel deleteGenre(Long filmId, Long genreId) throws FilmNotFoundException, GenreNotFoundException {
        FilmEntity film = filmRepo.findById(filmId).orElseThrow(
                () -> new FilmNotFoundException("Фильм не найден")
        );

        film.deleteGenre(genreRepo.findById(genreId).orElseThrow(
                () -> new GenreNotFoundException("Жанр не найден")
        ));

        return FilmModel.toModel(filmRepo.save(film));
    }

    public FilmModel addPerson(Long filmId, Long personId) throws FilmNotFoundException, PersonNotFoundException {
        FilmEntity film = filmRepo.findById(filmId).orElseThrow(
                () -> new FilmNotFoundException("Фильм не найден")
        );

        film.addPerson(personRepo.findById(personId).orElseThrow(
                () -> new PersonNotFoundException("Участник не найден")
        ));

        return FilmModel.toModel(filmRepo.save(film));
    }

    public FilmModel deletePerson(Long filmId, Long personId) throws FilmNotFoundException, PersonNotFoundException {
        FilmEntity film = filmRepo.findById(filmId).orElseThrow(
                () -> new FilmNotFoundException("Фильм не найден")
        );

        film.deletePerson(personRepo.findById(personId).orElseThrow(
                () -> new PersonNotFoundException("Участник не найден")
        ));

        return FilmModel.toModel(filmRepo.save(film));
    }

    public FilmModel rate(Long filmId, byte score, String username) throws FilmNotFoundException {

        UserEntity user = userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь не найден")
        );

        FilmEntity film = filmRepo.findById(filmId).orElseThrow(
                () -> new FilmNotFoundException("Фильм не найден")
        );

        UserRatingEntity userRatingEntity = new UserRatingEntity();


        if(userRatingRepo.existsByFilmAndUser(film, user)){
            userRatingEntity = userRatingRepo.findByFilmAndUser(film, user).orElse(new UserRatingEntity());
        }
        else{
            userRatingEntity.setFilm(film);
            userRatingEntity.setUser(user);
        }

        if(score > 5) {
            score = 5;
        }
        else if(score < 1){
            score = 1;
        }

        userRatingEntity.setScore(score);
        userRatingRepo.save(userRatingEntity);
        return FilmModel.toModel(filmRepo.findById(filmId).orElseThrow(
                () -> new FilmNotFoundException("Фильм не найден")
        ));
    }
}
