package com.praktika.cinema.service;

import com.praktika.cinema.entity.PersonEntity;
import com.praktika.cinema.exception.person.PersonAlreadyExistsException;
import com.praktika.cinema.exception.person.PersonNotFoundException;
import com.praktika.cinema.exception.person.ProfessionNotFoundException;
import com.praktika.cinema.model.PersonCUModel;
import com.praktika.cinema.model.PersonModel;
import com.praktika.cinema.repository.PersonRepo;
import com.praktika.cinema.repository.ProfessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private ProfessionRepo professionRepo;

    public PersonModel create(PersonCUModel person, Long professionId) throws ProfessionNotFoundException, PersonAlreadyExistsException {
        if(personRepo.existsByFullName(person.getFullName())){
            throw new PersonAlreadyExistsException("Участник уже существует");
        }

        PersonEntity personEntity = new PersonEntity();
        personEntity.setFullName(person.getFullName());
        personEntity.setProfession(professionRepo.findById(professionId).orElseThrow(
                () -> new ProfessionNotFoundException("Профессия не найдена")
        ));

        return PersonModel.toModel(personRepo.save(personEntity));
    }

    public Long delete(Long id){
        personRepo.deleteById(id);
        return id;
    }

    public PersonModel update(PersonCUModel person, Long personId) throws PersonNotFoundException {
        PersonEntity personEntity = personRepo.findById(personId).orElseThrow(
                () -> new PersonNotFoundException("Участник не найден")
        );
        personEntity.setFullName(person.getFullName());
        personEntity.setProfession(personEntity.getProfession());
        return PersonModel.toModel(personRepo.save(personEntity));
    }

    public List<PersonModel> getAll(){
        List<PersonModel> persons = new ArrayList<>();

        personRepo.findAll().forEach(personEntity -> {
            persons.add(PersonModel.toModel(personEntity));
        });

        return persons;
    }

    public PersonModel getOne(Long id) throws PersonNotFoundException {
        return PersonModel.toModel(personRepo.findById(id).orElseThrow(
                () -> new PersonNotFoundException("Участник фильма не найден")
        ));
    }
}
