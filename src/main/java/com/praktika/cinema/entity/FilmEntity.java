package com.praktika.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 1500)
    private String description;
    private int durationSeconds;
    private Long budget;
    private Date realeaseDate;
    private String country;
    private String ageLimit;
    @ManyToMany
    @JoinTable(name="film_persons",
            joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="person_id"))
    private List<PersonEntity> persons;
    @ManyToMany
    @JoinTable(name="film_genres",
            joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id"))
    private List<GenreEntity> genres;
    @OneToMany(mappedBy = "film")
    private List<UserRatingEntity> userRatings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Date getRealeaseDate() {
        return realeaseDate;
    }

    public void setRealeaseDate(Date realeaseDate) {
        this.realeaseDate = realeaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public List<PersonEntity> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonEntity> persons) {
        this.persons = persons;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }

    public List<UserRatingEntity> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<UserRatingEntity> userRatings) {
        this.userRatings = userRatings;
    }

    @Override
    public String toString() {
        return "FilmEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", durationSeconds=" + durationSeconds +
                ", budget=" + budget +
                ", realeaseDate=" + realeaseDate +
                ", country='" + country + '\'' +
                ", ageLimit='" + ageLimit + '\'' +
                ", persons=" + persons +
                ", genres=" + genres +
                ", userRatings=" + userRatings +
                '}';
    }
}
