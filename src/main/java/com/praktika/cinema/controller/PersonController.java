package com.praktika.cinema.controller;

import com.praktika.cinema.entity.PersonEntity;
import com.praktika.cinema.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity createPerson(@RequestBody PersonEntity personEntity, @RequestParam Long professionId){
        try{
            return ResponseEntity.ok(personService.create(personEntity, professionId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity updatePerson(@RequestBody PersonEntity personEntity){
        try{
            return ResponseEntity.ok(personService.update(personEntity));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOnePerson(@PathVariable Long id){
        try{
            return ResponseEntity.ok(personService.getOne(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllPerson(){
        try{
            return ResponseEntity.ok(personService.getAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка " + e);
        }
    }

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