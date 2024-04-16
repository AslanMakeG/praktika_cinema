package com.praktika.cinema.controller;

import com.praktika.cinema.entity.GenreEntity;
import com.praktika.cinema.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity createGenre(@RequestBody GenreEntity genreEntity){
        try {
            return ResponseEntity.ok(genreService.create(genreEntity));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllGenre(){
        try {
            return ResponseEntity.ok(genreService.getAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneGenre(@PathVariable Long id){
        try {
            return ResponseEntity.ok(genreService.getOne(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity updateGenre(@RequestBody GenreEntity genreEntity){
        try {
            return ResponseEntity.ok(genreService.update(genreEntity));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity deleteGenre(@PathVariable Long id){
        try {
            return ResponseEntity.ok(genreService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }
}
