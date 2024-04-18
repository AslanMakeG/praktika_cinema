package com.praktika.cinema.controller;

import com.praktika.cinema.entity.FilmEntity;
import com.praktika.cinema.service.FilmService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("/filter")
    public ResponseEntity getAllFilmsWithFilter(@Nullable @RequestParam(required = false) List<Long> genreIds,
                                                @Nullable @RequestParam(required = false) List<Long> personIds){
        try{
            return ResponseEntity.ok(filmService.filter(genreIds, personIds));
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

    @PutMapping("/deleteGenre")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity deleteGenreFromFilm(@RequestParam Long filmId, @RequestParam Long genreId){
        try{
            return ResponseEntity.ok(filmService.deleteGenre(filmId, genreId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @PutMapping("/addPerson")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity addPersonToFilm(@RequestParam Long filmId, @RequestParam Long personId){
        try{
            return ResponseEntity.ok(filmService.addPerson(filmId, personId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @PutMapping("/deletePerson")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity deletePersonFromFilm(@RequestParam Long filmId, @RequestParam Long personId){
        try{
            return ResponseEntity.ok(filmService.deletePerson(filmId, personId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @PostMapping("/{filmId}/rate")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD') or hasRole('USER')")
    public ResponseEntity rateFilm(@PathVariable Long filmId, @RequestParam byte score, Principal principal){
        try{
            return ResponseEntity.ok(filmService.rate(filmId, score, principal.getName()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }
}
