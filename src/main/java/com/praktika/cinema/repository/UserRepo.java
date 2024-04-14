package com.praktika.cinema.repository;

import com.praktika.cinema.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByUsername(String name);
    public Boolean existsByUsername(String name);
}
