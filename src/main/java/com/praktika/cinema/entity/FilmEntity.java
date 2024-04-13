package com.praktika.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "table")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer durationSeconds;
    private Long budget;
    private Date realeaseDate;
    private String country;
    private String ageLimit;
    @ManyToMany
    @JoinTable(name="film_persons",
            joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="person_id"))
    private List<PersonEntity> persons;
    @ManyToMany(mappedBy = "film")
    @JoinTable(name="film_genres",
            joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id"))
    private List<GenreEntity> genres;
    @OneToMany(mappedBy = "film")
    private List<UserRatingEntity> userRatings;
}
