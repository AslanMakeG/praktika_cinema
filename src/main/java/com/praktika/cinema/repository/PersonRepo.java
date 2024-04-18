package com.praktika.cinema.repository;

import com.praktika.cinema.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<PersonEntity, Long> {
    public Boolean existsByFullName(String name);
}
