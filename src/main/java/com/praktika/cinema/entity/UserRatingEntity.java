package com.praktika.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_rating")
public class UserRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte score;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmEntity film;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
