package com.praktika.cinema.controller;

import com.praktika.cinema.model.FilmCUModel;
import com.praktika.cinema.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Tag(name = "Фильмы",
        description = "Содержит CRUD операции для фильмов, добавления/удаления жанров и участников фильма," +
                " оценку фильма пользователями, а так же фильтрацию фильмов по жанрам и участникам.")
@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @Operation(
            summary = "Содает новый фильм",
            description = "Получает FilmCreationModel и записывает его в БД. " +
                    "Изначально фильм не содержит никаких участников и жанров."
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity createFilm(@RequestBody FilmCUModel film){
        try{
            return ResponseEntity.ok(filmService.create(film));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @Operation(
            summary = "Возвращает фильм по id"
    )
    @GetMapping("/{id}")
    public ResponseEntity getOneFilm(@PathVariable Long id){
        try{
            return ResponseEntity.ok(filmService.getOne(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @Operation(
            summary = "Возвращает все созданные фильмы"
    )
    @GetMapping("/all")
    public ResponseEntity getAllFilms(){
        try{
            return ResponseEntity.ok(filmService.getAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @Operation(
            summary = "Возвращает фильмы с фильтрацией",
            description = "Получает список с Id жанров и список с Id участников. " +
                    "Производит фильтрацию и возвращает все фильмы, которые содержат эти жанры и участников." +
                    "И список жанров, и список участников могут быть пустыми одновременно или по отдельности."
    )
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

    @Operation(
            summary = "Изменяет уже существующий фильм"
    )
    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity updateFilm(@RequestBody FilmCUModel film, @RequestParam Long filmId){
        try{
            return ResponseEntity.ok(filmService.update(film, filmId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @Operation(
            summary = "Удаляет существующий фильм по Id"
    )
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

    @Operation(
            summary = "Добавляет фильму жанр",
            description = "Получает Id фильма и Id жанра, который нужно добавить. Возвращает фильм."
    )
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

    @Operation(
            summary = "Удаляет у фильма жанр",
            description = "Получает Id фильма и Id жанра, который нужно удалить. Возвращает фильм."
    )
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

    @Operation(
            summary = "Добавляет фильму участника",
            description = "Получает Id фильма и Id участника, который нужно добавить. Возвращает фильм."
    )
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

    @Operation(
            summary = "Удаляет у фильма участника",
            description = "Получает Id фильма и Id участника, который нужно удалить. Возвращает фильм."
    )
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

    @Operation(
            summary = "Оценка фильма пользователем",
            description = "Получает Id фильма и оценку, которую передал авторизованный пользователь. " +
                    "Если оценка пользователя уже существует и он передал значение от 1 до 5, " +
                    "то оценка изменится на переданное значение. Если оценка существует, но передано значение 0, " +
                    "то оценка будет удалена. Если оценки не существует и передано значение от 1 до 5," +
                    " то оценка будет создана в БД."
    )
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
