package com.praktika.cinema.repository;

import com.praktika.cinema.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    public Optional<RoleEntity> findByName(String name);
}
