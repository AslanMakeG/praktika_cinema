package com.praktika.cinema.repository;

import com.praktika.cinema.entity.FilmEntity;
import com.praktika.cinema.entity.UserEntity;
import com.praktika.cinema.entity.UserRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRatingRepo extends JpaRepository<UserRatingEntity, Long> {
    boolean existsByFilmAndUser(FilmEntity film, UserEntity user);
    Optional<UserRatingEntity> findByFilmAndUser(FilmEntity film, UserEntity user);
}
