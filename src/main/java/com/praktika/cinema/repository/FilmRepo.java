package com.praktika.cinema.repository;

import com.praktika.cinema.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepo extends JpaRepository<FilmEntity, Long> {

    //@Query(value = "SELECT * FROM film f " +
    //        "JOIN film_genres g ON g.film_id = f.id " +
    //        "JOIN film_persons p ON p.film_id = f.id " +
    //        "WHERE (?1 IS NULL OR (g.id in ?1)) " +
    //        "AND (?2 IS NULL OR (p.id in ?2))", nativeQuery = true)
    //List<FilmEntity> findByFilter(List<Long> genreIds, List<Long> personIds);
}
