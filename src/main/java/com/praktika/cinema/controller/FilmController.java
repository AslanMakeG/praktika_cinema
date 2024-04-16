package com.praktika.cinema.controller;

import com.praktika.cinema.entity.FilmEntity;
import com.praktika.cinema.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity createFilm(@RequestBody FilmEntity film){
        try{
            return ResponseEntity.ok(filmService.create(film));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneFilm(@PathVariable Long id){
        try{
            return ResponseEntity.ok(filmService.getOne(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllFilms(){
        try{
            return ResponseEntity.ok(filmService.getAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @GetMapping("/genre")
    public ResponseEntity getAllFilmsWithGenres(@RequestParam List<Long> genreIds){
        try{
            return ResponseEntity.ok(filmService.getAllWithGenres(genreIds));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity updateFilm(@RequestBody FilmEntity film){
        try{
            return ResponseEntity.ok(filmService.update(film));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity deleteFilm(@PathVariable Long id){
        try{
            return ResponseEntity.ok(filmService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @PutMapping("/addGenre")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity addGenreToFilm(@RequestParam Long filmId, @RequestParam Long genreId){
        try{
            return ResponseEntity.ok(filmService.addGenre(filmId, genreId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }
}
