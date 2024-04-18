package com.praktika.cinema.service;

import com.praktika.cinema.entity.GenreEntity;
import com.praktika.cinema.exception.genre.GenreAlreadyExistsException;
import com.praktika.cinema.exception.genre.GenreNotFoundException;
import com.praktika.cinema.exception.person.PersonAlreadyExistsException;
import com.praktika.cinema.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepo genreRepo;

    public GenreEntity create(GenreEntity genreEntity) throws GenreAlreadyExistsException {
        if(genreRepo.existsByName(genreEntity.getName())){
            throw new GenreAlreadyExistsException("Жанр уже существует");
        }

        return genreRepo.save(genreEntity);
    }

    public Long delete(Long id){
        genreRepo.deleteById(id);
        return id;
    }

    public GenreEntity update(GenreEntity genreEntity){
        return genreRepo.save(genreEntity);
    }

    public List<GenreEntity> getAll(){
        return genreRepo.findAll();
    }

    public GenreEntity getOne(Long id) throws GenreNotFoundException {
        return genreRepo.findById(id).orElseThrow(
                () -> new GenreNotFoundException("Жанр не найден")
        );
    }
}
