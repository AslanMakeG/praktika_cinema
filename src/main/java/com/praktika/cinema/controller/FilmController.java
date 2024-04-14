package com.praktika.cinema.controller;

import com.praktika.cinema.entity.FilmEntity;
import com.praktika.cinema.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
