package com.praktika.cinema.model;

import com.praktika.cinema.entity.FilmEntity;
import com.praktika.cinema.entity.GenreEntity;
import com.praktika.cinema.entity.PersonEntity;
import com.praktika.cinema.entity.UserRatingEntity;

import java.util.*;

public class FilmModel {
    private Long id;
    private String name;
    private String description;
    private String duration;
    private Long budget;
    private Date realeaseDate;
    private String country;
    private String ageLimit;
    private float score;
    private Set<GenreEntity> genres = new HashSet<>();
    private Set<PersonModel> persons = new HashSet<>();

    public static FilmModel toModel(FilmEntity filmEntity){
        FilmModel model = new FilmModel();
        model.setId(filmEntity.getId());
        model.setName(filmEntity.getName());
        model.setDescription(filmEntity.getDescription());
        model.setBudget(filmEntity.getBudget());
        model.setRealeaseDate(filmEntity.getRealeaseDate());
        model.setCountry(filmEntity.getCountry());
        model.setAgeLimit(filmEntity.getAgeLimit());
        model.setDuration(getDurationInHM(filmEntity.getDurationSeconds()));
        model.setScore(getAverageScore(filmEntity));
        model.setGenres(filmEntity.getGenres());
        if(filmEntity.getPersons() != null) {
            for (PersonEntity person : filmEntity.getPersons()) {
                model.addPerson(person);
            }
        }
        else {
            model.setPersons(null);
        }
        return model;
    }

    private static String getDurationInHM(Integer durationSeconds){
        int hours = durationSeconds / 3600;
        int minutes = (durationSeconds - (hours * 3600)) / 60;
        return String.format("%1$s ч. %2$s м.", hours, minutes);
    }

    private static float getAverageScore(FilmEntity filmEntity){
        if(filmEntity.getUserRatings() == null)
            return 0;
        float score = 0;
        for(UserRatingEntity rating : filmEntity.getUserRatings()){
            score += rating.getScore();
        }

        return filmEntity.getUserRatings().size() == 0 ? 0 : score / filmEntity.getUserRatings().size();
    }

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void addPerson(PersonEntity personEntity){
        this.persons.add(PersonModel.toModel(personEntity));
    }

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEntity> genres) {
        this.genres = genres;
    }

    public Set<PersonModel> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonModel> persons) {
        this.persons = persons;
    }
}
