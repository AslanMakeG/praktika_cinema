package com.praktika.cinema.repository;

import com.praktika.cinema.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<PersonEntity, Long> {
}
