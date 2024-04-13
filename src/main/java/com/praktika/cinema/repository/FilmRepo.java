package com.praktika.cinema.repository;

import com.praktika.cinema.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepo extends JpaRepository<FilmEntity, Long> {
}
