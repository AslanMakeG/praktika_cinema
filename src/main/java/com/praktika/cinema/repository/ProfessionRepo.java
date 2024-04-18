package com.praktika.cinema.repository;

import com.praktika.cinema.entity.ProfessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepo extends JpaRepository<ProfessionEntity, Long> {
    public Boolean existsByName(String name);
}
