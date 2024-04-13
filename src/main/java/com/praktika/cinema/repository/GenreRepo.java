package com.praktika.cinema.repository;

import com.praktika.cinema.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<GenreEntity, Long> {
}
