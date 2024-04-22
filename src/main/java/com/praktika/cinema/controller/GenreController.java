package com.praktika.cinema.controller;

import com.praktika.cinema.entity.GenreEntity;
import com.praktika.cinema.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Жанры",
        description = "Содержит CRUD операции для жанров.")
@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @Operation(
            summary = "Создает новый жанр фильма",
            description = "Получает GenreEntity и записывает в БД."
    )
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

    @Operation(
            summary = "Возвращает все созданные жанры"
    )
    @GetMapping("/all")
    public ResponseEntity getAllGenre(){
        try {
            return ResponseEntity.ok(genreService.getAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @Operation(
            summary = "Возвращает жанр по id"
    )
    @GetMapping("/{id}")
    public ResponseEntity getOneGenre(@PathVariable Long id){
        try {
            return ResponseEntity.ok(genreService.getOne(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @Operation(
            summary = "Изменяет уже существующий жанр фильма",
            description = "Получает GenreEntity с указанным id, изменяет поля и записывает изменения в БД."
    )
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

    @Operation(
            summary = "Удаляет жанр по id"
    )
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
