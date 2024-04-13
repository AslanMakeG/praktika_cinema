package com.praktika.cinema.repository;

import com.praktika.cinema.entity.UserRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRatingRepo extends JpaRepository<UserRatingEntity, Long> {
}
