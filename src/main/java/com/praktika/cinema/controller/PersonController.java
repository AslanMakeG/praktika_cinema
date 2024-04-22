package com.praktika.cinema.controller;

import com.praktika.cinema.entity.PersonEntity;
import com.praktika.cinema.model.PersonCUModel;
import com.praktika.cinema.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Участники фильма",
        description = "Содержит CRUD операции для участников.")
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Operation(
            summary = "Добавляет нового участника фильма",
            description = "Получает PersonEntity и professionId (целое число типа Long) и записывает " +
                    "участника в БД, добавляя ему указанную профессию. " +
                    "У каждого участника может быть только 1 профессия."
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity createPerson(@RequestBody PersonCUModel personEntity, @RequestParam Long professionId){
        try{
            return ResponseEntity.ok(personService.create(personEntity, professionId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @Operation(
            summary = "Изменяет существующего участника фильма",
            description = "Получает PersonEntity с указанным id. По этому id находит его в БД, изменяет " +
                    "поля и сохраняет измененного участника."
    )
    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity updatePerson(@RequestBody PersonCUModel person, @RequestParam Long personId){
        try{
            return ResponseEntity.ok(personService.update(person, personId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @Operation(
            summary = "Возвращает участника по указанному id"
    )
    @GetMapping("/{id}")
    public ResponseEntity getOnePerson(@PathVariable Long id){
        try{
            return ResponseEntity.ok(personService.getOne(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @Operation(
            summary = "Возвращает всех участников"
    )
    @GetMapping("/all")
    public ResponseEntity getAllPerson(){
        try{
            return ResponseEntity.ok(personService.getAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @Operation(
            summary = "Удаляет участника по id"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity deletePerson(@PathVariable Long id){
        try{
            return ResponseEntity.ok(personService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }
}