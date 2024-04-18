package com.praktika.cinema.repository;

import com.praktika.cinema.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepo extends JpaRepository<GenreEntity, Long> {
    public Boolean existsByName(String name);
}
