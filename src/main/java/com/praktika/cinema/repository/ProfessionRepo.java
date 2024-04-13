package com.praktika.cinema.repository;

import com.praktika.cinema.entity.ProfessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepo extends JpaRepository<ProfessionEntity, Long> {
}
